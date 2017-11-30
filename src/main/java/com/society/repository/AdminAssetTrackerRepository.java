package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.AssetContactJPA;
import com.society.model.jpa.AssetScanFileJPA;
import com.society.model.jpa.AssetServiceHistoryJPA;
import com.society.model.jpa.AssetTrackerJPA;

@Repository
public class AdminAssetTrackerRepository extends BaseRepository {
	
	public boolean saveAssetTrackerEntry(AssetTrackerJPA asset) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(asset);
			
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
	
	public List<AssetTrackerJPA> getAssetList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AssetTrackerJPA> criteriaQuery = criteriaBuilder.createQuery(AssetTrackerJPA.class);
		Root<AssetTrackerJPA> root = criteriaQuery.from(AssetTrackerJPA.class);
		
		Predicate societyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		criteriaQuery.where(societyIdPredicate);
		
		List<AssetTrackerJPA> assetList;
		try {
			assetList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			assetList = null;
		}
		return assetList;
	}
	
	public AssetTrackerJPA getAsset(Integer assetId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AssetTrackerJPA> criteriaQuery = criteriaBuilder.createQuery(AssetTrackerJPA.class);
		Root<AssetTrackerJPA> root = criteriaQuery.from(AssetTrackerJPA.class);
		
		Predicate assetIdPredicate = criteriaBuilder.equal(root.<Integer>get("assetId"), assetId);
		criteriaQuery.where(assetIdPredicate);
		
		AssetTrackerJPA asset;
		try {
			asset = entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch(Exception e) {
			asset = null;
		}
		return asset;
	}
}
