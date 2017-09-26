package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.model.jpa.SocietyUserAccessRightsJPA;
import com.society.model.jpa.UserJPA;

@Repository
public class LoginRepository extends BaseRepository {
	
	public UserJPA validateLogin(UserJPA userJPA) {
	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UserJPA> criteriaQuery = criteriaBuilder.createQuery(UserJPA.class);
		Root<UserJPA> root = criteriaQuery.from(UserJPA.class);
		root.fetch("role", JoinType.INNER);
		root.fetch("society", JoinType.LEFT);
		criteriaQuery.select(root);
		
		Predicate userNamePredicate = criteriaBuilder.equal(root.<String> get("userName"), userJPA.getUserName());
		Predicate userPasswordPredicate = criteriaBuilder.equal(root.<String> get("userPassword"), userJPA.getUserPassword());
		
		Predicate andPredicate = criteriaBuilder.and(userNamePredicate, userPasswordPredicate);
		
		criteriaQuery.where(andPredicate);
		
		UserJPA user;
		try {
			user = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			user = null;
		}
		return user;
	}
	
	public List<SocietyUserAccessRightsJPA> getMenuAccess(UserJPA user) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SocietyUserAccessRightsJPA> criteriaQuery = criteriaBuilder.createQuery(SocietyUserAccessRightsJPA.class);
		Root<SocietyUserAccessRightsJPA> root = criteriaQuery.from(SocietyUserAccessRightsJPA.class);
		criteriaQuery.select(root);
		
		Predicate userIdPredicate = criteriaBuilder.equal(root.<Integer> get("accessRightsId").get("userId"), user.getUserId());
		criteriaQuery.where(userIdPredicate);
		
		List<SocietyUserAccessRightsJPA> accessRights;
		try {
			accessRights = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			accessRights = null;
		}
		return accessRights;
	}
	
}
