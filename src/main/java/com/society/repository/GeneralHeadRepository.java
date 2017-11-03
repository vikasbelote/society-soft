package com.society.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadOrderJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;
import com.society.model.jpa.TransactionJPA;

@Repository
public class GeneralHeadRepository extends BaseRepository {
	
	public List<GeneralHeadSectionJPA> getSectionList() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadSectionJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadSectionJPA.class);
		Root<GeneralHeadSectionJPA> root = criteriaQuery.from(GeneralHeadSectionJPA.class);
		criteriaQuery.select(root);
		
		List<GeneralHeadSectionJPA> generalHeadSectionList;
		try {
			generalHeadSectionList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadSectionList = null;
		}
		return generalHeadSectionList;
	}
	
	
	public List<GeneralHeadJPA> getGeneralHeadList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		Join<GeneralHeadJPA, GeneralHeadSectionJPA> sectionList = root.join("section", JoinType.INNER);
		sectionList.join("report", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate nullSocietyIdPredicate = criteriaBuilder.isNull(root.<Integer>get("society").get("societyId"));
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		
		Predicate orPredicate = criteriaBuilder.or(nullSocietyIdPredicate, equalSocietyIdPredicate);
		
		criteriaQuery.where(orPredicate);
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}
	
	
	public boolean insertGeneralHead(GeneralHeadJPA generalHead) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.saveOrUpdate(generalHead);
			
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
	
	public boolean deleteGeneralHead(GeneralHeadJPA generalHead) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			
			session.delete(session.merge(generalHead));
			
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
	
	public GeneralHeadJPA checkGeneralHeadExist(GeneralHeadDomain generalHeadDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		root.fetch("section", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate generalHeadIdPredicate = criteriaBuilder.notEqual(root.<Integer>get("generalHeadId"), generalHeadDomain.getGeneralHeadId());
		Predicate generalHeadNamePredicate = criteriaBuilder.equal(root.<String>get("generalHeadName"), generalHeadDomain.getGeneralHeadName());
		Predicate sectionIdPredicate = criteriaBuilder.equal(root.<Integer>get("section").get("sectionId"), generalHeadDomain.getSectionId());
		
		Predicate andPredicate = criteriaBuilder.and(generalHeadNamePredicate, sectionIdPredicate);
		
		if(generalHeadDomain.getGeneralHeadId() == null)
			criteriaQuery.where(andPredicate);
		else
			criteriaQuery.where(generalHeadIdPredicate, andPredicate);
		
		GeneralHeadJPA generalHead;
		try {
			generalHead = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			generalHead = null;
		}
		return generalHead;
	}
	
}
