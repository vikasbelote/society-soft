package com.society.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.SocietyMemberJPA;

@Repository
public class MaintenanceBillStatusRepository extends BaseRepository {
	
	public List<MaintenanceReceiptJPA> getMaintenanceReceiptList(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceReceiptJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaQuery.from(MaintenanceReceiptJPA.class);
		Fetch<MaintenanceReceiptJPA, SocietyMemberJPA> member =  root.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycle").get("cycleId"), cycleId);
		criteriaQuery.where(equalCycleIdPredicate);
		
		List<MaintenanceReceiptJPA> receiptList;
		try {
			receiptList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			receiptList = null;
		}
		return receiptList;
	}
	
	public MaintenanceCycleJPA getCycleDetails(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceCycleJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceCycleJPA.class);
		Root<MaintenanceCycleJPA> root = criteriaQuery.from(MaintenanceCycleJPA.class);
		Fetch<MaintenanceCycleJPA, List<MaintenanceReceiptJPA>> receiptList =  root.fetch("receiptList", JoinType.INNER);
		Fetch< List<MaintenanceReceiptJPA>, SocietyMemberJPA> member =  receiptList.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycleId"), cycleId);
		criteriaQuery.where(equalCycleIdPredicate);
		
		MaintenanceCycleJPA cycle;
		try {
			cycle = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			cycle = null;
		}
		return cycle;
	}
	
	public boolean updateBillStatus(List<MaintenanceReceiptJPA> receiptList) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			 
			for(MaintenanceReceiptJPA receipt : receiptList) {
				Query query = session.createQuery("update MaintenanceReceiptJPA set billStatus=:billStatus,paidAmount=:paidAmount  where receipId=:receipId");
				query.setParameter("billStatus", receipt.getBillStatus());
				query.setParameter("receipId", receipt.getReceipId());
				query.setParameter("paidAmount", receipt.getPaidAmount());
				query.executeUpdate();
			}
			
			//update outstanding amount also
			
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
