package com.society.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.society.constant.SectionEnum;
import com.society.model.domain.EmailDomain;
import com.society.model.domain.MaintenacneChargeDomain;
import com.society.model.domain.MaintenacneNoteDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenanceReceiptDomain;
import com.society.model.domain.StatusMemberDomain;
import com.society.model.jpa.AddressJPA;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;
import com.society.model.jpa.MaintenanceChargeJPA;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceCycleNoteJPA;
import com.society.model.jpa.MaintenanceHeadJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.MemberOutAmountJPA;
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
	
	public boolean saveMaintenanceData(Integer cycleId, List<MaintenanceChargeJPA> chargeList, List<MaintenanceCycleNoteJPA> noteCycle) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			for(MaintenanceChargeJPA charge : chargeList) {
				session.saveOrUpdate(charge);
			}
			
			if(CollectionUtils.isNotEmpty(noteCycle)) {
				
				if(cycleId != null) {
					Query query = session.createQuery("delete MaintenanceCycleNoteJPA where cycle.cycleId = :cycleId");
					query.setParameter("cycleId", cycleId);
					query.executeUpdate();
				}

				for(MaintenanceCycleNoteJPA note : noteCycle) {
					session.saveOrUpdate(note);
				}
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
	
	public boolean updtaeMaintenanceData(MaintenanceCycleReceiptDomain cycleDomain) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			SocietyJPA society = session.load(SocietyJPA.class, cycleDomain.getSocietyId());
			MaintenanceCycleJPA cycle = session.load(MaintenanceCycleJPA.class, cycleDomain.getCycleId());
			cycle.setSociety(society);
			cycle.setPaymentDueDate(cycleDomain.getPaymentDueDate());
			cycle.setStartDate(cycleDomain.getStartDate());
			cycle.setEndDate(cycleDomain.getEndDate());
			
			for(MaintenanceReceiptDomain receiptDomain : cycleDomain.getReceipts()) {
				SocietyMemberJPA member = session.load(SocietyMemberJPA.class, receiptDomain.getMemberId());
				
				MaintenanceReceiptJPA receipt = session.load(MaintenanceReceiptJPA.class, receiptDomain.getReceiptId());
				receipt.setCycle(cycle);
				receipt.setMember(member);
				receipt.setBillNumber(receiptDomain.getBillNumber());
				
				double totalAmount = 0;
				for(MaintenacneChargeDomain maintenacneChargeDomain : receiptDomain.getChargeList()) {
					
					MaintenanceHeadJPA maintenanceHead = session.load(MaintenanceHeadJPA.class, maintenacneChargeDomain.getMaintenanceHeadId());
					MaintenanceChargeJPA charge = session.load(MaintenanceChargeJPA.class, maintenacneChargeDomain.getChargeId());
					charge.setReceipt(receipt);
					charge.setMaintenanceHead(maintenanceHead);
					charge.setChargeValue(maintenacneChargeDomain.getChargeValue());
					session.update(charge);
					
					totalAmount = totalAmount + (maintenacneChargeDomain.getChargeValue() == null ? 0 : maintenacneChargeDomain.getChargeValue());
				}
				receipt.setTotalAmount(totalAmount);
			}
			
			if(CollectionUtils.isNotEmpty(cycleDomain.getNotes())) {
				
				Query query = session.createQuery("delete MaintenanceCycleNoteJPA where cycle.cycleId = :cycleId");
				query.setParameter("cycleId", cycleDomain.getCycleId());
				query.executeUpdate();
				
				for(MaintenacneNoteDomain note : cycleDomain.getNotes()) {
					MaintenanceCycleNoteJPA noteDB = new MaintenanceCycleNoteJPA();
					noteDB.setNoteText(note.getNoteText());
					noteDB.setCycle(cycle);
					session.save(noteDB);
				}
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
	
	public Map<Integer, Double> getOutstandingAmount(MaintenanceDomain maintenanceDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
		Root<MaintenanceCycleJPA> root = criteriaQuery.from(MaintenanceCycleJPA.class);	
		Join<MaintenanceCycleJPA, MaintenanceReceiptJPA> receipt = root.join("receiptList", JoinType.INNER);
		
		Predicate isNullOutAmount = criteriaBuilder.isNull(receipt.<Double>get("outAmount"));
		Predicate isNullPaidAmount = criteriaBuilder.isNull(receipt.<Double>get("paidAmount"));
		Predicate isNullOutAndPaidAmount = criteriaBuilder.and(isNullOutAmount, isNullPaidAmount);
		
		Expression<Double> diffTotalAndPaidAmount = criteriaBuilder.diff(receipt.<Double>get("totalAmount"), receipt.<Double>get("paidAmount"));
		Expression<Double> addTotalAndOutAmount = criteriaBuilder.sum(receipt.<Double>get("totalAmount"), receipt.<Double>get("outAmount"));
		Expression<Double> addTotalAndOutAmountAndDiffPaid = criteriaBuilder.diff(addTotalAndOutAmount, receipt.<Double>get("paidAmount"));
		
		Expression<Double> sumExpression = criteriaBuilder.<Double>selectCase().when(isNullOutAndPaidAmount, receipt.<Double>get("totalAmount"))
																			   .when(isNullOutAmount, diffTotalAndPaidAmount)
																			   .when(isNullPaidAmount, addTotalAndOutAmount)
																			   .otherwise(addTotalAndOutAmountAndDiffPaid);
		
		criteriaQuery.multiselect(receipt.<Integer>get("member").get("memberId"), sumExpression);
		
		Predicate startDatePredicate = criteriaBuilder.between(root.<Date>get("startDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate endDatePredicate = criteriaBuilder.between(root.<Date>get("endDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), maintenanceDomain.getSocietyId());
		Predicate billStatusPredicate = criteriaBuilder.equal(receipt.<Boolean>get("billStatus"), false);
		Predicate isActivePredicate = criteriaBuilder.equal(receipt.<Boolean>get("isActive"), true);
		
		criteriaQuery.where(startDatePredicate, endDatePredicate, equalSocietyIdPredicate, billStatusPredicate, isActivePredicate);
		criteriaQuery.groupBy(receipt.<Integer>get("member").get("memberId"));
		
		List<Tuple> groupByResult;
		Map<Integer, Double> outStandingMap = new HashMap<Integer, Double>();
		try {
			groupByResult = entityManager.createQuery(criteriaQuery).getResultList();
			for(Tuple tuple : groupByResult) {
				Integer memberId = (Integer)tuple.get(0);
				Double outstandingAmount = (Double)tuple.get(1);
				outStandingMap.put(memberId, outstandingAmount);
			}
		}
		catch(Exception e) {
			groupByResult = null;
		}
		return outStandingMap;
	}
	
	@Transactional
	public boolean updateActiveFlag(MaintenanceDomain maintenanceDomain) {
				
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<MaintenanceReceiptJPA> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaUpdate.from(MaintenanceReceiptJPA.class);
		criteriaUpdate.set(root.<Boolean>get("isActive"), false);
		
		//sub query section start
		Subquery<MaintenanceCycleJPA> subquery = criteriaUpdate.subquery(MaintenanceCycleJPA.class);
		Root<MaintenanceCycleJPA> subqueryRoot = subquery.from(MaintenanceCycleJPA.class);
		subquery.select(subqueryRoot.get("cycleId"));
		
		Predicate startDatePredicate = criteriaBuilder.between(subqueryRoot.<Date>get("startDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate endDatePredicate = criteriaBuilder.between(subqueryRoot.<Date>get("endDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(subqueryRoot.<Integer>get("society").get("societyId"), maintenanceDomain.getSocietyId());
		
		subquery.where(startDatePredicate, endDatePredicate, equalSocietyIdPredicate);
		//sub query section end
		
		Predicate cycleIdPredicate = root.get("cycle").in(subquery);
		criteriaUpdate.where(cycleIdPredicate);
		
		//Update active flag for maintenance cycle
		CriteriaUpdate<MaintenanceCycleJPA> criteriaUpdateCycle = criteriaBuilder.createCriteriaUpdate(MaintenanceCycleJPA.class);
		Root<MaintenanceCycleJPA> rootCycle = criteriaUpdateCycle.from(MaintenanceCycleJPA.class);
		criteriaUpdateCycle.set(rootCycle.<Boolean>get("isActive"), false);
		
		Predicate startDatePredicateCycle = criteriaBuilder.between(rootCycle.<Date>get("startDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate endDatePredicateCycle = criteriaBuilder.between(rootCycle.<Date>get("endDate"), maintenanceDomain.getPaymentCycleStartDate(), maintenanceDomain.getCycleStartDate());
		Predicate equalSocietyIdPredicateCycle = criteriaBuilder.equal(rootCycle.<Integer>get("society").get("societyId"), maintenanceDomain.getSocietyId());
		
		criteriaUpdateCycle.where(startDatePredicateCycle, endDatePredicateCycle, equalSocietyIdPredicateCycle);
	
		boolean isSuccess = false;
		try {
			entityManager.createQuery(criteriaUpdate).executeUpdate();
			entityManager.createQuery(criteriaUpdateCycle).executeUpdate();
			isSuccess = true;;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
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
	
	public List<MaintenanceCycleNoteJPA> getAdditionalNote(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceCycleNoteJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceCycleNoteJPA.class);
		Root<MaintenanceCycleNoteJPA> root = criteriaQuery.from(MaintenanceCycleNoteJPA.class);	
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycle").get("cycleId"), cycleId);
		criteriaQuery.where(equalCycleIdPredicate);
		
		List<MaintenanceCycleNoteJPA> noteList;
		try {
			noteList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			noteList = null;
		}
		return noteList;
	}
	
	public Set<MaintenanceReceiptJPA> getMaintenanceReceipt(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceReceiptJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaQuery.from(MaintenanceReceiptJPA.class);	
		Fetch<MaintenanceReceiptJPA, List<MaintenanceChargeJPA>> chargeList = root.fetch("chargeList", JoinType.INNER);
		chargeList.fetch("maintenanceHead", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, MaintenanceCycleJPA> cycle = root.fetch("cycle", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, SocietyJPA> society = cycle.fetch("society", JoinType.INNER);
		society.fetch("address", JoinType.INNER);
		society.fetch("societyConfig", JoinType.INNER);
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
	
	public Set<MaintenanceReceiptJPA> getMaintenanceReceiptTable(Integer cycleId, List<Integer> memberIdList) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceReceiptJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaQuery.from(MaintenanceReceiptJPA.class);	
		Fetch<MaintenanceReceiptJPA, List<MaintenanceChargeJPA>> chargeList = root.fetch("chargeList", JoinType.INNER);
		chargeList.fetch("maintenanceHead", JoinType.INNER);
		root.fetch("cycle", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, SocietyMemberJPA> member = root.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycle").get("cycleId"), cycleId);
		if(CollectionUtils.isNotEmpty(memberIdList)) {
			Predicate memberIdsPredicate = root.<Integer>get("member").get("memberId").in(memberIdList);
			criteriaQuery.where(equalCycleIdPredicate, memberIdsPredicate);
		}
		else 
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
	
	public List<MemberOutAmountJPA> getMemberOutstandingAmount(Integer cycleId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberOutAmountJPA> criteriaQuery = criteriaBuilder.createQuery(MemberOutAmountJPA.class);
		Root<MemberOutAmountJPA> root = criteriaQuery.from(MemberOutAmountJPA.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Integer>get("cycleId"), cycleId));
		
		List<MemberOutAmountJPA> outAmountList;
		try {
			outAmountList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			outAmountList = null;
		}
		return outAmountList;
	}
	
	public Set<MaintenanceReceiptJPA> getMemberMaintenanceReceipt(EmailDomain email) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MaintenanceReceiptJPA> criteriaQuery = criteriaBuilder.createQuery(MaintenanceReceiptJPA.class);
		Root<MaintenanceReceiptJPA> root = criteriaQuery.from(MaintenanceReceiptJPA.class);	
		Fetch<MaintenanceReceiptJPA, List<MaintenanceChargeJPA>> chargeList = root.fetch("chargeList", JoinType.INNER);
		chargeList.fetch("generalHead", JoinType.INNER);
		root.fetch("cycle", JoinType.INNER);
		Fetch<MaintenanceReceiptJPA, SocietyMemberJPA> member = root.fetch("member", JoinType.INNER);
		member.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		List<Integer> memberIdList = new ArrayList<Integer>();
		for(StatusMemberDomain statusMember : email.getMemberIds()) {
			memberIdList.add(statusMember.getMemberId());
		}
		
		
		Predicate memberIdsPredicate = root.<Integer>get("member").get("memberId").in(memberIdList);
		Predicate equalCycleIdPredicate = criteriaBuilder.equal(root.<Integer>get("cycle").get("cycleId"), email.getCycleId());
		criteriaQuery.where(equalCycleIdPredicate, memberIdsPredicate);
		
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
	
	public boolean deleteCycleDetails(Integer cycleId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Serializable id = new Integer(cycleId);
			Object persistentInstance = session.load(MaintenanceCycleJPA.class, id);
			if (persistentInstance != null) 
			    session.delete(persistentInstance);			
			else
				return false;
			
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
