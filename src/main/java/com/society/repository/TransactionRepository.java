package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyMemberJPA;
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
		root.join("transactionType", JoinType.INNER);
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
	
	public boolean insertTransactionEntry(TransactionJPA transaction) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
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
}
