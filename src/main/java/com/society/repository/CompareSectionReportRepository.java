package com.society.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.model.domain.CompareSectionReportDomain;
import com.society.model.jpa.GeneralHeadOrderJPA;
import com.society.model.jpa.TransactionJPA;

@Repository
public class CompareSectionReportRepository extends BaseRepository {
	
	public List<TransactionJPA> getBalanceSheetData(CompareSectionReportDomain compareSectionReportDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TransactionJPA> criteriaQuery = criteriaBuilder.createQuery(TransactionJPA.class);
		Root<TransactionJPA> root = criteriaQuery.from(TransactionJPA.class);
		root.fetch("transactionDescription", JoinType.INNER);
		root.fetch("generalHead", JoinType.INNER).fetch("section" , JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate reportName = criteriaBuilder.equal(root.<String>get("generalHead").get("section").get("report").get("reportName"), compareSectionReportDomain.getReportName());
		Predicate societyId = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), compareSectionReportDomain.getSocietyId());
		Predicate transactionDate = criteriaBuilder.between(root.<Date>get("transactionDate"), compareSectionReportDomain.getLastYearStartDate(), compareSectionReportDomain.getCurrentYearEndDate());
		
		criteriaQuery.where(reportName, societyId, transactionDate);
		
		List<TransactionJPA> transactionList;
		try {
			transactionList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			transactionList = null;
		}
		return transactionList;
	}
	
	public List<GeneralHeadOrderJPA> getGeneralHeadOrder(CompareSectionReportDomain compareSectionReportDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadOrderJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadOrderJPA.class);
		Root<GeneralHeadOrderJPA> root = criteriaQuery.from(GeneralHeadOrderJPA.class);
		root.fetch("generalHead", JoinType.INNER);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.<Integer>get("sequenceNumber")));
		
		Predicate reportNamePredicate = criteriaBuilder.equal(root.<String>get("generalHead").get("section").get("report").get("reportName"), compareSectionReportDomain.getReportName());
		Predicate societyId = criteriaBuilder.equal(root.<Integer>get("societyConfig").get("society").get("societyId"), compareSectionReportDomain.getSocietyId());
		
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
}
