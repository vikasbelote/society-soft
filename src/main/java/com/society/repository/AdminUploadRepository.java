package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.AdminUploadDomain;
import com.society.model.jpa.AdminUploadJPA;

@Repository
public class AdminUploadRepository extends BaseRepository {

	public List<AdminUploadJPA> getCommonFile(AdminUploadDomain uploadDomain) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdminUploadJPA> criteriaQuery = criteriaBuilder.createQuery(AdminUploadJPA.class);
		Root<AdminUploadJPA> root = criteriaQuery.from(AdminUploadJPA.class);
		criteriaQuery.select(root);
		
		//Predicate fileTypePredicate = criteriaBuilder.equal(root.<String>get("fileType"), uploadDomain.getFileType());
		Predicate societyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), uploadDomain.getSocietyId());
		
		criteriaQuery.where(societyIdPredicate);
		
		List<AdminUploadJPA> uploadList;
		try {
			uploadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			uploadList = null;
		}
		return uploadList;
	}
	
	public boolean saveUploadFileDetails(AdminUploadJPA upload) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(upload);
			
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
