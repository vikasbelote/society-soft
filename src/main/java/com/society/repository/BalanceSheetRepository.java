package com.society.repository;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.TransactionJPA;

@Repository
public class BalanceSheetRepository extends BaseRepository {

	public List<TransactionJPA> getBalanceSheetData(Integer societyId){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransactionJPA> criteriaQuery = criteriaBuilder.createQuery(TransactionJPA.class);
		Root<TransactionJPA> root = criteriaQuery.from(TransactionJPA.class);
		root.join("generalHead", JoinType.INNER);
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
	
	public List<GeneralHeadJPA> getBalanceSheetData1(Integer societyId){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		Join<GeneralHeadJPA, TransactionJPA> transactionList = root.join("transactionList", JoinType.INNER);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(transactionList.<Integer>get("society").get("societyId"), societyId));
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}
	
}
