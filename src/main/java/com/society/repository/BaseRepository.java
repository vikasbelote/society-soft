package com.society.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepository {

	@Autowired
	protected EntityManager entityManager;

	@Autowired
	protected SessionFactory sessionFactory;

	public <T> List<T> getMasterList(Class<T> clazz) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		criteria.select(root);

		return entityManager.createQuery(criteria).getResultList();
	}
	

	public <T> T getModel(int entryId, Class<T> clazz) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.<Integer> get("rowId"), new Integer(entryId)));

		return entityManager.createQuery(criteria).getSingleResult();
	}
	
	public <T> Set<T> convertToSet(List<T> list, Class<T> clazz) {
		
		Set<T> set = new HashSet<T>(list);
		return set;
	}
}
