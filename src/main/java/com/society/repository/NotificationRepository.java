package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.model.jpa.EmailStatusJPA;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.SocietyMemberJPA;

@Repository
public class NotificationRepository extends BaseRepository {
	
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
	
	public List<EmailStatusJPA> getEmailStatusList(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmailStatusJPA> criteriaQuery = criteriaBuilder.createQuery(EmailStatusJPA.class);
		Root<EmailStatusJPA> root = criteriaQuery.from(EmailStatusJPA.class);
		Fetch<EmailStatusJPA, MaintenanceReceiptJPA> receipt = root.fetch("receipt", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, SocietyMemberJPA> member = receipt.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("receipt").get("cycle").get("cycleId"), cycleId);
		criteriaQuery.where(equalCycleIdPredicate);
		
		List<EmailStatusJPA> emailStatusList;
		try {
			emailStatusList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			emailStatusList = null;
		}
		return emailStatusList;
	}
}
