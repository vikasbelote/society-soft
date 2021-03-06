package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.SocietyConfigDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadOrderJPA;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyMemberJPA;

@Repository
public class SocietyConfigRepository extends BaseRepository  {

	public List<GeneralHeadJPA> getGeneralHeadList(String reportName) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		root.fetch("section", JoinType.INNER);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<String>get("section").get("report").get("reportName"), reportName));
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}
	
	public SocietyConfigJPA getSocietyConfig(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyConfigJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyConfigJPA.class);
		Root<SocietyConfigJPA> root = criteriaQuery.from(SocietyConfigJPA.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId));
		SocietyConfigJPA societyConfig;
		try {
			societyConfig =  entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			societyConfig = null;
		}
		return societyConfig;
	}
	
	public List<GeneralHeadOrderJPA> getGeneralHeadOrder(SocietyConfigDomain societyConfigDomian, String reportName) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadOrderJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadOrderJPA.class);
		Root<GeneralHeadOrderJPA> root = criteriaQuery.from(GeneralHeadOrderJPA.class);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.<Integer>get("sequenceNumber")));
		
		Predicate reportNamePredicate = criteriaBuilder.equal(root.<String>get("generalHead").get("section").get("report").get("reportName"), reportName);
		Predicate societyId = criteriaBuilder.equal(root.<Integer>get("societyConfig").get("configId"), societyConfigDomian.getConfigId());
		
		criteriaQuery.where(reportNamePredicate, societyId);
		
		List<GeneralHeadOrderJPA> generalHeadOrderList;
		try {
			generalHeadOrderList =  entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadOrderList = null;
		}
		return generalHeadOrderList;
	}
	
	public boolean saveSocietyConfig(List<GeneralHeadOrderJPA> generalHeadOrderList, SocietyConfigJPA societyConfig) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.saveOrUpdate(societyConfig);
			for(GeneralHeadOrderJPA generalHeadOrder : generalHeadOrderList) {
				session.saveOrUpdate(generalHeadOrder);
			}
			
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e) {
			if(session != null)
				session.getTransaction().rollback();
			return false;
		}
		finally {
			if(session != null)
				session.close();
		}
		
	}
}
