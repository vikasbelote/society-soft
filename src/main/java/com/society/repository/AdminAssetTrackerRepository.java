package com.society.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.domain.AdminAssertContactDomain;
import com.society.model.domain.AdminAssetScanFileDomain;
import com.society.model.domain.AdminAssetServiceHistoryDomain;
import com.society.model.domain.AdminAssetTrackerDomain;
import com.society.model.jpa.AssetCategoryJPA;
import com.society.model.jpa.AssetContactJPA;
import com.society.model.jpa.AssetScanFileJPA;
import com.society.model.jpa.AssetServiceHistoryJPA;
import com.society.model.jpa.AssetTrackerJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.UserJPA;
import com.society.utils.SocietyUtils;

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
	
	public boolean updateAssetDetails(AdminAssetTrackerDomain assetDomain) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			AssetCategoryJPA category = session.load(AssetCategoryJPA.class, assetDomain.getCategoryId());
			
			AssetTrackerJPA asset = session.load(AssetTrackerJPA.class, assetDomain.getAssetId());
			asset.setAssetName(assetDomain.getAssetName());
			asset.setAssetTagNumber(assetDomain.getAssetTagNumber());
			asset.setVendorName(assetDomain.getVendorName());
			asset.setCategory(category);
			asset.setAssetLocation(assetDomain.getAssetLocation());
			asset.setPurchaseDate(assetDomain.getPurchaseDate());
			asset.setAssetCost(assetDomain.getAssetCost());
			asset.setAssetStatus(assetDomain.getAssetStatus());
			
			
			for(AdminAssertContactDomain contactDomain : assetDomain.getContactDomainList()) {
				
				PersonJPA person = new PersonJPA();
				person.setPersonId(contactDomain.getPerson().getPersonId());
				person.setFirstName(contactDomain.getPerson().getFirstName());
				person.setMiddleName(contactDomain.getPerson().getMiddleName());
				person.setLastName(contactDomain.getPerson().getLastName());
				person.setContactNumber(contactDomain.getPerson().getContactNumber());
				person.setEmailId(contactDomain.getPerson().getEmailId());
				
				AssetContactJPA contact = new AssetContactJPA();
				contact.setAsset(asset);
				contact.setPerson(person);
				
				if(contactDomain.getContactId() == null) {
					session.save(contact);
				}
				else {
					contact.setContactId(contactDomain.getContactId());
					if(contactDomain.getIsUpdated()) {
						session.update(contact);
					}
					if(contactDomain.getIsDeleted()) {
						session.delete(session.merge(contact));
					}
				}
			}
			
			for(AdminAssetServiceHistoryDomain serviceHistoryDomain : assetDomain.getServiceHistoryDomainList()) {
				
				PersonJPA person = new PersonJPA();
				person.setPersonId(serviceHistoryDomain.getPerson().getPersonId());
				person.setFirstName(serviceHistoryDomain.getPerson().getFirstName());
				person.setMiddleName(serviceHistoryDomain.getPerson().getMiddleName());
				person.setLastName(serviceHistoryDomain.getPerson().getLastName());
				person.setContactNumber(serviceHistoryDomain.getPerson().getContactNumber());
				person.setEmailId(serviceHistoryDomain.getPerson().getEmailId());
				
				AssetServiceHistoryJPA serviceHistory = new AssetServiceHistoryJPA();
				serviceHistory.setHistoryDate(serviceHistoryDomain.getHistoryDate());
				serviceHistory.setAsset(asset);
				serviceHistory.setPerson(person);
				
				if(serviceHistoryDomain.getServiceHistoryId() == null){
					session.save(serviceHistory);
				}
				else {
					serviceHistory.setServiceHistoryId(serviceHistoryDomain.getServiceHistoryId());
					if(serviceHistoryDomain.getIsUpdated()) {
						session.update(serviceHistory);
					}
					if(serviceHistoryDomain.getIsDeleted()) {
						session.delete(session.merge(serviceHistory));
					}
				}
			}
			
			for(AdminAssetScanFileDomain scanFileDomain : assetDomain.getScanFileDomainList()) {
				
				UserJPA user = new UserJPA();
				user.setUserId(scanFileDomain.getUser().getUserId());
				
				AssetScanFileJPA scanFile = new AssetScanFileJPA();
				scanFile.setFileId(scanFileDomain.getFileId());
				scanFile.setAsset(asset);
				scanFile.setUser(user);
				scanFile.setFileName(scanFileDomain.getFileName());
				scanFile.setUploadStatus(scanFileDomain.getUploadStatus());
				
				if(scanFileDomain.getFileId() == null)
					session.save(scanFile);
				else {
					if(scanFileDomain.getIsDeleted())
						session.delete(session.merge(scanFile));
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
}
