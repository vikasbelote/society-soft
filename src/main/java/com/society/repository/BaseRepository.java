package com.society.repository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.MaintenanceCycleJPA;

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
	
	protected boolean deleteById(Class<?> type, Serializable id, Session session) {
	    Object persistentInstance = session.load(type, id);
	    if (persistentInstance != null) {
	        session.delete(persistentInstance);
	        return true;
	    }
	    return false;
	}
	
	public boolean deleteObjectById(Class<?> type, Integer deletedId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Serializable id = new Integer(deletedId);
			Object persistentInstance = session.load(type, id);
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
