package com.society.repository;


import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.constant.ReportEnum;
import com.society.model.domain.BalanceSheetDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;
import com.society.model.jpa.TransactionJPA;

@Repository
public class BalanceSheetRepository extends BaseRepository {
	
	public List<GeneralHeadJPA> getBalanceSheetData(BalanceSheetDomain balanceSheetDomain){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		Join<GeneralHeadJPA, TransactionJPA> transactionList = root.join("transactionList", JoinType.INNER);
		transactionList.join("transactionDescription", JoinType.INNER);
		Join<GeneralHeadJPA, GeneralHeadSectionJPA> sectionList = root.join("section", JoinType.INNER);
		sectionList.join("report", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate reportName = criteriaBuilder.equal(sectionList.<String>get("report").get("reportName"), ReportEnum.BS.value());
		Predicate societyId = criteriaBuilder.equal(transactionList.<Integer>get("society").get("societyId"), balanceSheetDomain.getSocietyId());
		Predicate transactionDate = criteriaBuilder.between(transactionList.<Date>get("transactionDate"), balanceSheetDomain.getLastYearStartDate(), balanceSheetDomain.getCurrentYearEndDate());
		
		criteriaQuery.where(reportName, societyId, transactionDate);
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}
	
	public List<GeneralHeadJPA> getBalanceSheetData1(BalanceSheetDomain balanceSheetDomain){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		criteriaQuery.select(root);
		
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
