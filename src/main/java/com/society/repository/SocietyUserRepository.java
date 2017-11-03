package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.SocietyUserDomain;
import com.society.model.jpa.AccessRightsId;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyUserAccessRightsJPA;
import com.society.model.jpa.UserJPA;

@Repository
public class SocietyUserRepository extends BaseRepository {
	
	public boolean insertSocietyUserDeatils(UserJPA user, List<SocietyUserAccessRightsJPA> rightList) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			if(user.getUserId() != null) {
				
				Query query = session.createQuery("delete SocietyUserAccessRightsJPA where accessRightsId.userId = :userIdForm");
				query.setParameter("userIdForm", user.getUserId());
				int result = query.executeUpdate();
				 
				if (result > 0) {
				    System.out.println("Expensive products was removed");
				}
			}
			
			session.saveOrUpdate(user);
			
			for(SocietyUserAccessRightsJPA accessRights : rightList) {
				accessRights.getAccessRightsId().setUserId(user.getUserId());
				session.saveOrUpdate(accessRights);
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
	
	public List<UserJPA> getSocietyUserList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserJPA> criteriaQuery = criteriaBuilder.createQuery(UserJPA.class);
		Root<UserJPA> root = criteriaQuery.from(UserJPA.class);
		root.fetch("role", JoinType.INNER);
		root.fetch("society", JoinType.INNER);
		root.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate societyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		
		criteriaQuery.where(societyIdPredicate);
		
		List<UserJPA> userList;
		try {
			userList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			userList = null;
		}
		return userList;
	}
	
	public UserJPA getSocietyUser(Integer userId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserJPA> criteriaQuery = criteriaBuilder.createQuery(UserJPA.class);
		Root<UserJPA> root = criteriaQuery.from(UserJPA.class);
		root.fetch("role", JoinType.INNER);
		root.fetch("society", JoinType.INNER);
		root.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate userIdPredicate = criteriaBuilder.equal(root.<Integer>get("userId"), userId);
		criteriaQuery.where(userIdPredicate);
		
		UserJPA user;
		try {
			user = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			user = null;
		}
		return user;
	}
	
	public List<SocietyUserAccessRightsJPA> getUserRights(Integer userId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyUserAccessRightsJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyUserAccessRightsJPA.class);
		Root<SocietyUserAccessRightsJPA> root = criteriaQuery.from(SocietyUserAccessRightsJPA.class);
		criteriaQuery.select(root);
		
		Predicate userIdPredicate = criteriaBuilder.equal(root.<Integer>get("accessRightsId").get("userId"), userId);
		criteriaQuery.where(userIdPredicate);
		
		List<SocietyUserAccessRightsJPA> menuRightList;
		try {
			menuRightList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			menuRightList = null;
		}
		return menuRightList;
	}
	
	public UserJPA checkUserExist(SocietyUserDomain societyUser) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserJPA> criteriaQuery = criteriaBuilder.createQuery(UserJPA.class);
		Root<UserJPA> root = criteriaQuery.from(UserJPA.class);
		root.fetch("society", JoinType.INNER);
		root.fetch("person", JoinType.INNER);
		criteriaQuery.select(root);
		
		
		Predicate userId = criteriaBuilder.notEqual(root.<Integer>get("userId"), societyUser.getUserId());
		Predicate userNamePredicate = criteriaBuilder.equal(root.<String>get("userName"), societyUser.getUserName());
		Predicate contactNumberPredicate = criteriaBuilder.equal(root.<String>get("person").get("contactNumber"), societyUser.getContactNumber());
		Predicate emailIdPredicate = criteriaBuilder.equal(root.<String>get("person").get("emailId"), societyUser.getEmailId());
		
		Predicate orPredicate = criteriaBuilder.or(userNamePredicate, contactNumberPredicate, emailIdPredicate);
		
		if(societyUser.getUserId() == null)
			criteriaQuery.where(orPredicate);
		else
			criteriaQuery.where(userId, orPredicate);
		
		UserJPA user;
		try {
			user = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			user = null;
		}
		return user;
	}
}
