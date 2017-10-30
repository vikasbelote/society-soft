package com.society.repository;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.constant.SectionEnum;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.jpa.AddressJPA;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;
import com.society.model.jpa.MaintenanceChargeJPA;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;

@Repository
public class MaintenanceRepository extends BaseRepository {
	
	public List<GeneralHeadJPA> getGeneralHeadList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		Join<GeneralHeadJPA, GeneralHeadSectionJPA> sectionList = root.join("section", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate nullSocietyIdPredicate = criteriaBuilder.isNull(root.<Integer>get("society").get("societyId"));
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		Predicate maintenanceSectionPredicate = criteriaBuilder.equal(sectionList.<String>get("sectionName"), SectionEnum.MA.value());
		
		Predicate orSocietyId = criteriaBuilder.or(nullSocietyIdPredicate, equalSocietyIdPredicate);
		
		Predicate orPredicate = criteriaBuilder.and(orSocietyId, maintenanceSectionPredicate);
		
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
	
	public List<GeneralHeadJPA> getGeneralHeadList(Map<Integer, String> generalHeadIdChargeMap) { 
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);		
		criteriaQuery.select(root);
		
		Predicate generalHead = root.get("generalHeadId").in(criteriaBuilder.keys(generalHeadIdChargeMap));
		
		criteriaQuery.where(generalHead);
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}
	
	public List<SocietyMemberJPA> getSocietyMemberList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyMemberJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyMemberJPA.class);
		Root<SocietyMemberJPA> root = criteriaQuery.from(SocietyMemberJPA.class);
		root.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		
		criteriaQuery.where(equalSocietyIdPredicate);
		
		List<SocietyMemberJPA> societyMemberList;
		try {
			societyMemberList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			societyMemberList = null;
		}
		return societyMemberList;
		
	}
	
	public SocietyJPA getSocietyDetail(Integer societyId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyJPA.class);
		Root<SocietyJPA> root = criteriaQuery.from(SocietyJPA.class);
		root.fetch("address", JoinType.INNER);
		criteriaQuery.select(root);
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("societyId"), societyId);
		criteriaQuery.where(equalSocietyIdPredicate);
		
		SocietyJPA Society;
		try {
			Society = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			Society = null;
		}
		return Society;
	}
	
	public SocietyConfigJPA getSocietyConfigDetail(Integer societyId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyConfigJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyConfigJPA.class);
		Root<SocietyConfigJPA> root = criteriaQuery.from(SocietyConfigJPA.class);
		Fetch<SocietyConfigJPA, SocietyJPA> society = root.fetch("society", JoinType.INNER);
		society.fetch("address",JoinType.INNER);
		criteriaQuery.select(root);
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		criteriaQuery.where(equalSocietyIdPredicate);
		
		SocietyConfigJPA societyConfig;
		try {
			societyConfig = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			societyConfig = null;
		}
		return societyConfig;
	}
	
	public boolean saveMaintenanceData(List<MaintenanceChargeJPA> chargeList) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			for(MaintenanceChargeJPA charge : chargeList) {
				session.saveOrUpdate(charge);
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
	
	public List<MaintenanceCycleJPA> checkPaymentCycleExist(MaintenanceDomain maintenanceDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceCycleJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceCycleJPA.class);
		Root<MaintenanceCycleJPA> root = criteriaQuery.from(MaintenanceCycleJPA.class);		
		criteriaQuery.select(root);
		
		Predicate cycleStartDate = criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("startDate"), maintenanceDomain.getPaymentCycleStartDate());
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), maintenanceDomain.getSocietyId());
		
		criteriaQuery.where(cycleStartDate, equalSocietyIdPredicate);
		
		List<MaintenanceCycleJPA> maintenanceCycleList;
		try {
			maintenanceCycleList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			maintenanceCycleList = null;
		}
		return maintenanceCycleList;
	}
	
	public List<MaintenanceCycleJPA> getMaintenanceCycleList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceCycleJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceCycleJPA.class);
		Root<MaintenanceCycleJPA> root = criteriaQuery.from(MaintenanceCycleJPA.class);		
		criteriaQuery.select(root);
		
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		criteriaQuery.where(equalSocietyIdPredicate);
		
		List<MaintenanceCycleJPA> maintenanceCycleList;
		try {
			maintenanceCycleList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			maintenanceCycleList = null;
		}
		return maintenanceCycleList;
	}
	
	public Set<MaintenanceReceiptJPA> getMaintenanceReceipt(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceReceiptJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaQuery.from(MaintenanceReceiptJPA.class);	
		Fetch<MaintenanceReceiptJPA, List<MaintenanceChargeJPA>> chargeList = root.fetch("chargeList", JoinType.INNER);
		chargeList.fetch("generalHead", JoinType.INNER);
		root.fetch("cycle", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, SocietyMemberJPA> member = root.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycle").get("cycleId"), cycleId);
		criteriaQuery.where(equalCycleIdPredicate);
		
		Set<MaintenanceReceiptJPA> maintenanceReceiptSet;
		try {
			List<MaintenanceReceiptJPA> maintenanceReceiptList = entityManager.createQuery(criteriaQuery).getResultList();
			maintenanceReceiptSet = new HashSet<MaintenanceReceiptJPA>(maintenanceReceiptList);
		}
		catch(Exception e) {
			maintenanceReceiptSet = null;
		}
		return maintenanceReceiptSet;
	}
}
