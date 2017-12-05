package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.SocietyMemberDomain;
import com.society.model.jpa.SocietyMemberJPA;

@Repository
public class SocietyMemberRepository extends BaseRepository {
	
	
	public List<SocietyMemberJPA> getSocietyMemberList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyMemberJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyMemberJPA.class);
		Root<SocietyMemberJPA> root = criteriaQuery.from(SocietyMemberJPA.class);
		root.fetch("person", JoinType.INNER);
		
		criteriaQuery.where(criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId));
		
		List<SocietyMemberJPA> memberList;
		try {
			memberList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			return null;
		}
		return memberList;
	}
	
	public boolean saveOrUpdateSocietyMemberDetails(SocietyMemberJPA member) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.saveOrUpdate(member);
				
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
	
	public SocietyMemberJPA getSocietyMemberDetails(SocietyMemberDomain memberDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyMemberJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyMemberJPA.class);
		Root<SocietyMemberJPA> root = criteriaQuery.from(SocietyMemberJPA.class);
		root.fetch("person", JoinType.INNER);
		root.fetch("additionalArea", JoinType.LEFT);
		
		Predicate societyId = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), memberDomain.getSocietyId());
		Predicate memberId = criteriaBuilder.equal(root.<Integer>get("memberId"), memberDomain.getMemberId());
		
		criteriaQuery.where(societyId, memberId);
		
		SocietyMemberJPA member;
		try {
			member = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			return null;
		}
		return member;
	}
	
	public SocietyMemberJPA checkMemberExist(SocietyMemberDomain memberDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyMemberJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyMemberJPA.class);
		Root<SocietyMemberJPA> root = criteriaQuery.from(SocietyMemberJPA.class);
		root.fetch("person", JoinType.INNER);
		
		
		Predicate firstName = criteriaBuilder.equal(root.<String>get("person").get("firstName"), memberDomain.getFirstName());
		Predicate lastName = criteriaBuilder.equal(root.<String>get("person").get("lastName"), memberDomain.getLastName());
		Predicate andFirstLastName = criteriaBuilder.and(firstName, lastName);
		
		Predicate mobileNumber = criteriaBuilder.equal(root.<String>get("person").get("contactNumber"), memberDomain.getMobileNumber());
		Predicate emailId = criteriaBuilder.equal(root.<String>get("person").get("emailId"), memberDomain.getEmailId());
		
		Predicate memberId = criteriaBuilder.notEqual(root.<Integer>get("memberId"), memberDomain.getMemberId());
		Predicate societyId = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), memberDomain.getSocietyId());
		Predicate personId = criteriaBuilder.notEqual(root.<Integer>get("person").get("personId"), memberDomain.getPersonId());
		
		Predicate orPerson = criteriaBuilder.or(andFirstLastName, mobileNumber, emailId);
		
		Predicate insertPredicate = criteriaBuilder.and(orPerson, societyId);
		Predicate updatePredicate = criteriaBuilder.and(orPerson, societyId, memberId, personId);
		
		if(memberDomain.getMemberId() == null) 
			criteriaQuery.where(insertPredicate);
		else
			criteriaQuery.where(updatePredicate);
		
		SocietyMemberJPA member;
		try {
			member = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			return null;
		}
		return member;
	}
}
