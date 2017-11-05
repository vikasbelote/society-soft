package com.society.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.TransactionDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.model.jpa.TransactionDescriptionJPA;
import com.society.model.jpa.TransactionJPA;

@Repository
public class TransactionRepository extends BaseRepository {
	
	public List<GeneralHeadJPA> getGeneralHeadList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
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
	
	public List<TransactionJPA> getTransactionEntry(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransactionJPA> criteriaQuery = criteriaBuilder.createQuery(TransactionJPA.class);
		Root<TransactionJPA> root = criteriaQuery.from(TransactionJPA.class);
		root.join("generalHead", JoinType.INNER);
		root.join("transactionDescription", JoinType.INNER);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId));
		
		List<TransactionJPA> transactionList;
		try {
			transactionList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			transactionList = null;
		}
		return transactionList;
	}
	
	public List<TransactionDescriptionJPA> getTransactionDescription(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransactionDescriptionJPA> criteriaQuery = criteriaBuilder.createQuery(TransactionDescriptionJPA.class);
		Root<TransactionDescriptionJPA> root = criteriaQuery.from(TransactionDescriptionJPA.class);
		root.join("generalHead", JoinType.INNER);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId));
		
		List<TransactionDescriptionJPA> transactionDescriptionList;
		try {
			transactionDescriptionList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			transactionDescriptionList = null;
		}
		return transactionDescriptionList;
	}
	
	public boolean insertTransactionEntry(TransactionJPA transaction , TransactionDescriptionJPA transactionDescription) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			if(transactionDescription.getDescId() == null)
				session.save(transactionDescription);
			
			session.saveOrUpdate(transaction);
			
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
	
	public boolean deleteTransactionEntry(TransactionJPA transaction) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.delete(session.merge(transaction));
			
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
	
	public TransactionJPA checkTransactionExist(TransactionDomain transactionDomain, List<Date> dateList) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransactionJPA> criteriaQuery = criteriaBuilder.createQuery(TransactionJPA.class);
		Root<TransactionJPA> root = criteriaQuery.from(TransactionJPA.class);
		criteriaQuery.select(root);
		
		Predicate transactionId = criteriaBuilder.notEqual(root.<Integer>get("transactionId"), transactionDomain.getTransactionId());
		Predicate generalHeadId = criteriaBuilder.equal(root.<Integer>get("generalHead").get("generalHeadId"), transactionDomain.getGeneralHeadId());
		Predicate transactionDescriptionId = criteriaBuilder.equal(root.<Integer>get("transactionDescription").get("descId"), transactionDomain.getTransactionDescriptionId());
		Predicate transactionDate = criteriaBuilder.between(root.<Date>get("transactionDate"), dateList.get(0), dateList.get(1));
		
		if(transactionDomain.getTransactionId() == null)
			criteriaQuery.where(generalHeadId, transactionDescriptionId, transactionDate);
		else
			criteriaQuery.where(transactionId, generalHeadId, transactionDescriptionId, transactionDate);
		
		TransactionJPA transaction;
		try {
			transaction = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			transaction = null;
		}
		return transaction;
	}
	
}
