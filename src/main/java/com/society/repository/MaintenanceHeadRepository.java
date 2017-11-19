package com.society.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.MaintenanceHeadDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.MaintenanceHeadChargeCalcJPA;
import com.society.model.jpa.MaintenanceHeadChargeCalcTypeJPA;
import com.society.model.jpa.MaintenanceHeadJPA;

@Repository
public class MaintenanceHeadRepository extends BaseRepository {
	

	public List<MaintenanceHeadChargeCalcTypeJPA> getMaintenanceHeadChargeCalcType() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceHeadChargeCalcTypeJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceHeadChargeCalcTypeJPA.class);
		Root<MaintenanceHeadChargeCalcTypeJPA> root = criteriaQuery.from(MaintenanceHeadChargeCalcTypeJPA.class);
		criteriaQuery.select(root);
		
		List<MaintenanceHeadChargeCalcTypeJPA> chargeTypeList;
		try {
			chargeTypeList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			chargeTypeList = null;
		}
		return chargeTypeList;
	}
	
	public List<MaintenanceHeadJPA> getMaintenanceHeadList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceHeadJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceHeadJPA.class);
		Root<MaintenanceHeadJPA> root = criteriaQuery.from(MaintenanceHeadJPA.class);
		Fetch<MaintenanceHeadJPA, MaintenanceHeadChargeCalcJPA> calcution = root.fetch("calculation", JoinType.INNER);
		calcution.fetch("calcType", JoinType.INNER);
		calcution.fetch("referenceMaintenanceHead", JoinType.LEFT);
		criteriaQuery.select(root);
		
		Predicate nullSocietyIdPredicate = criteriaBuilder.isNull(root.<Integer>get("society").get("societyId"));
		Predicate societyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		Predicate orPredicate = criteriaBuilder.or(nullSocietyIdPredicate, societyIdPredicate);
		
		criteriaQuery.where(orPredicate);
		
		List<MaintenanceHeadJPA> maintenanceHeadList;
		try {
			maintenanceHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			maintenanceHeadList = null;
		}
		return maintenanceHeadList;
	}
	
	public boolean saveMaintenanceHead(MaintenanceHeadJPA maintenanceHead) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.saveOrUpdate(maintenanceHead);
			
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
	
	public boolean deleteMaintenanceHead(MaintenanceHeadJPA maintenanceHead) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Serializable id = new Integer(maintenanceHead.getHeadId());		
			boolean isSuccess = this.deleteById(MaintenanceHeadJPA.class, id, session);
			
			session.getTransaction().commit();
			return isSuccess;
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
	
	public MaintenanceHeadJPA checkMaintenanceHeadExist(MaintenanceHeadDomain maintenanceHeadDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceHeadJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceHeadJPA.class);
		Root<MaintenanceHeadJPA> root = criteriaQuery.from(MaintenanceHeadJPA.class);
		criteriaQuery.select(root);
		
		Predicate maintenanceHeadIdPredicate = criteriaBuilder.notEqual(root.<Integer>get("headId"), maintenanceHeadDomain.getMaintenanceHeadId());
		Predicate maintenanceHeadNamePredicate = criteriaBuilder.equal(root.<String>get("headName"), maintenanceHeadDomain.getMaintenanceHeadName());
		
		if(maintenanceHeadDomain.getMaintenanceHeadId() == null)
			criteriaQuery.where(maintenanceHeadNamePredicate);
		else
			criteriaQuery.where(maintenanceHeadIdPredicate, maintenanceHeadNamePredicate);
		
		MaintenanceHeadJPA maintenanceHead;
		try {
			maintenanceHead = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			maintenanceHead = null;
		}
		return maintenanceHead;
	}

}
