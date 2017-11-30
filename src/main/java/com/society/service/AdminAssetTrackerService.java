package com.society.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.society.model.domain.AdminAssertContactDomain;
import com.society.model.domain.AdminAssetAlertDomain;
import com.society.model.domain.AdminAssetScanFileDomain;
import com.society.model.domain.AdminAssetServiceHistoryDomain;
import com.society.model.domain.AdminAssetTrackerDomain;
import com.society.model.domain.AssetCategoryDomain;
import com.society.model.domain.PersonDomain;
import com.society.model.domain.UserDomain;
import com.society.model.jpa.AssetAlertJPA;
import com.society.model.jpa.AssetCategoryJPA;
import com.society.model.jpa.AssetContactJPA;
import com.society.model.jpa.AssetScanFileJPA;
import com.society.model.jpa.AssetServiceHistoryJPA;
import com.society.model.jpa.AssetTrackerJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.UserJPA;
import com.society.repository.AdminAssetTrackerRepository;
import com.society.utils.SocietyUtils;

@Service
public class AdminAssetTrackerService {
	
	private static final Logger logger = LogManager.getLogger(AdminAssetTrackerService.class);
	
	@Autowired
	private AdminAssetTrackerRepository adminAssetTrackerRepository;
	
	public List<AssetCategoryDomain> getCategoryList() {
		
		List<AssetCategoryJPA> categoryList = adminAssetTrackerRepository.getMasterList(AssetCategoryJPA.class);
		if(CollectionUtils.isEmpty(categoryList))
			return null;
		
		List<AssetCategoryDomain> categoryDomainList = new ArrayList<AssetCategoryDomain>();
		for(AssetCategoryJPA category : categoryList) {
			AssetCategoryDomain categoryDomain = new AssetCategoryDomain();
			categoryDomain.setCategoryId(category.getCategoryId());
			categoryDomain.setCategoryName(category.getCategoryName());
			categoryDomain.setCategoryCode(category.getCategoryCode());
			categoryDomainList.add(categoryDomain);
		}
		return categoryDomainList;
	}
	
	
	public boolean saveAssetTrackerEntry(AdminAssetTrackerDomain adminAssetTrackerDomain, MultipartFile[] files,
			String[] scanFileNames) {
		
		AssetCategoryJPA category = new AssetCategoryJPA();
		category.setCategoryId(SocietyUtils.setNullIfZero(adminAssetTrackerDomain.getCategoryId()));
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(adminAssetTrackerDomain.getSocietyId());
		
		AssetTrackerJPA asset = new AssetTrackerJPA();
		asset.setSociety(society);
		asset.setAssetName(adminAssetTrackerDomain.getAssetName());
		asset.setAssetTagNumber(adminAssetTrackerDomain.getAssetTagNumber());
		asset.setVendorName(adminAssetTrackerDomain.getVendorName());
		asset.setCategory(category);
		asset.setAssetLocation(adminAssetTrackerDomain.getAssetLocation());
		asset.setPurchaseDate(adminAssetTrackerDomain.getPurchaseDate());
		asset.setAssetCost(adminAssetTrackerDomain.getAssetCost());
		asset.setAssetStatus(adminAssetTrackerDomain.getAssetStatus());
		
		List<AssetContactJPA> assetContactList =  new ArrayList<AssetContactJPA>();
		if(ArrayUtils.isNotEmpty(adminAssetTrackerDomain.getContactFirstName())) {
			for(int i = 0; i < adminAssetTrackerDomain.getContactFirstName().length; i++) {
			
				PersonJPA person = new PersonJPA();
				person.setFirstName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getContactFirstName()));
				person.setMiddleName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getContactMiddleName()));
				person.setLastName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getContactLastName()));
				person.setContactNumber(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getContactMobileNumber()));
				person.setEmailId(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getContactEmailId()));
				
				AssetContactJPA contact = new AssetContactJPA();
				contact.setAsset(asset);
				contact.setPerson(person);
				
				assetContactList.add(contact);
			}
		}
		if(CollectionUtils.isNotEmpty(assetContactList))
			asset.setContactList(assetContactList);
		
		List<AssetServiceHistoryJPA> assetServiceHistoryList = new ArrayList<AssetServiceHistoryJPA>();
		if(ArrayUtils.isNotEmpty(adminAssetTrackerDomain.getServiceFirstName())) {
			for(int i = 0; i < adminAssetTrackerDomain.getServiceFirstName().length; i++) {
				
				PersonJPA person = new PersonJPA();
				person.setFirstName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getServiceFirstName()));
				person.setMiddleName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getServiceMiddleName()));
				person.setLastName(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getServiceLastName()));
				person.setContactNumber(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getServiceMobileNumber()));
				person.setEmailId(SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getServiceEmailId()));
				
				AssetServiceHistoryJPA assetServiceHistoryJPA = new AssetServiceHistoryJPA();
				assetServiceHistoryJPA.setPerson(person);
				assetServiceHistoryJPA.setAsset(asset);
				String historyDateStr = SocietyUtils.getIndexValue(i, adminAssetTrackerDomain.getHistoryDate());
				assetServiceHistoryJPA.setHistoryDate(Date.valueOf(historyDateStr));
				
				assetServiceHistoryList.add(assetServiceHistoryJPA);
			}
		}
		if(CollectionUtils.isNotEmpty(assetServiceHistoryList))
			asset.setServiceHistoryList(assetServiceHistoryList);
		
		List<AssetScanFileJPA> scanFileList = new ArrayList<AssetScanFileJPA>();
		if(ArrayUtils.isNotEmpty(files) && StringUtils.isNotBlank(adminAssetTrackerDomain.getRootPath())) {
			
			String directoryName = adminAssetTrackerDomain.getRootPath().concat(adminAssetTrackerDomain.getSocietyName());
			File directory = new File(directoryName);
			if (!directory.exists()) {
		    	logger.info("Directory does not exist");
		        directory.mkdir();
		        logger.info("Directory get created : " + directoryName);
		    }
			
			UserJPA user = new UserJPA();
			user.setUserId(adminAssetTrackerDomain.getUserId());
			
			for (int i = 0; i < files.length; i++) {
				
				MultipartFile file = files[i];
				String filename = scanFileNames[i];
				
		        AssetScanFileJPA scanFile = new AssetScanFileJPA();
				scanFile.setAsset(asset);
				scanFile.setUser(user);
				scanFile.setFileName(filename);
				try {
			        byte[] bytes = file.getBytes();
			        Path path = Paths.get(directoryName + "/" + filename);
			        logger.info("writing file content");
			        Files.write(path, bytes);
			        logger.info("successfuly write file content");
					scanFile.setUploadStatus(true);
			    }
			    catch (IOException e){
			    	logger.debug("error in uploading file : " + e.getMessage());
			    	scanFile.setUploadStatus(false);
			    }
				scanFileList.add(scanFile);
			}
		}
		if(CollectionUtils.isNotEmpty(scanFileList))
			asset.setScanFileList(scanFileList);
		
		return adminAssetTrackerRepository.saveAssetTrackerEntry(asset);
	}
	
	public List<AdminAssetTrackerDomain> getAssetList(Integer societyId) {
		
		List<AssetTrackerJPA> assetList = adminAssetTrackerRepository.getAssetList(societyId);
		if(CollectionUtils.isEmpty(assetList))
			return null;
		
		List<AdminAssetTrackerDomain> assetDomainList = new ArrayList<AdminAssetTrackerDomain>();
		for(AssetTrackerJPA asset : assetList) {
			AdminAssetTrackerDomain assetDomain = new AdminAssetTrackerDomain();
			assetDomain.setAssetId(asset.getAssetId());
			assetDomain.setAssetName(asset.getAssetName());
			assetDomain.setAssetTagNumber(asset.getAssetTagNumber());
			assetDomain.setVendorName(asset.getVendorName());
			assetDomain.setPurchaseDate(asset.getPurchaseDate());
			assetDomain.setAssetCost(asset.getAssetCost());
			assetDomain.setAssetLocation(asset.getAssetLocation());
			assetDomain.setAssetStatus(asset.getAssetStatus());
			assetDomainList.add(assetDomain);
		}
		return assetDomainList;
	}
	
	public AdminAssetTrackerDomain getAssetDomain(Integer assetId) {
		
		AssetTrackerJPA asset = adminAssetTrackerRepository.getAsset(assetId);
		if(asset == null)
			return null;
		
		AdminAssetTrackerDomain assetDomain = new AdminAssetTrackerDomain();
		assetDomain.setAssetId(asset.getAssetId());
		assetDomain.setAssetName(asset.getAssetName());
		assetDomain.setAssetTagNumber(asset.getAssetTagNumber());
		assetDomain.setVendorName(asset.getVendorName());
		assetDomain.setPurchaseDate(asset.getPurchaseDate());
		assetDomain.setAssetCost(asset.getAssetCost());
		assetDomain.setAssetLocation(asset.getAssetLocation());
		assetDomain.setAssetStatus(asset.getAssetStatus());
		assetDomain.setCategoryId(asset.getCategory().getCategoryId());
		
		List<AdminAssertContactDomain> contactDomainList = new ArrayList<AdminAssertContactDomain>();
		for(AssetContactJPA contact : asset.getContactList()) {
			
			PersonDomain person = new PersonDomain();
			person.setPersonId(contact.getPerson().getPersonId());
			person.setFirstName(contact.getPerson().getFirstName());
			person.setMiddleName(contact.getPerson().getMiddleName());
			person.setLastName(contact.getPerson().getLastName());
			person.setContactNumber(contact.getPerson().getContactNumber());
			person.setEmailId(contact.getPerson().getEmailId());
			
			AdminAssertContactDomain contactDomain = new AdminAssertContactDomain();
			contactDomain.setContactId(contact.getContactId());
			contactDomain.setAssetId(asset.getAssetId());
			contactDomain.setPerson(person);
			
			contactDomainList.add(contactDomain);
		}
		assetDomain.setContactDomainList(contactDomainList);
		
		List<AdminAssetServiceHistoryDomain> serviceHistoryDomainList = new ArrayList<AdminAssetServiceHistoryDomain>();
		for(AssetServiceHistoryJPA serviceHistory : asset.getServiceHistoryList()) {
			
			PersonDomain person = new PersonDomain();
			person.setPersonId(serviceHistory.getPerson().getPersonId());
			person.setFirstName(serviceHistory.getPerson().getFirstName());
			person.setMiddleName(serviceHistory.getPerson().getMiddleName());
			person.setLastName(serviceHistory.getPerson().getLastName());
			person.setContactNumber(serviceHistory.getPerson().getContactNumber());
			person.setEmailId(serviceHistory.getPerson().getEmailId());
			
			AdminAssetServiceHistoryDomain serviceHistoryDomain = new AdminAssetServiceHistoryDomain();
			serviceHistoryDomain.setHistoryDate(serviceHistory.getHistoryDate());
			serviceHistoryDomain.setServiceHistoryId(serviceHistory.getServiceHistoryId());
			serviceHistoryDomain.setPerson(person);
			serviceHistoryDomain.setAddtionalInfo(serviceHistory.getAddtionalInfo());
			serviceHistoryDomain.setAssetId(asset.getAssetId());
			
			serviceHistoryDomainList.add(serviceHistoryDomain);
		}
		assetDomain.setServiceHistoryDomainList(serviceHistoryDomainList);
		
		List<AdminAssetAlertDomain> alertDomainList = new ArrayList<AdminAssetAlertDomain>();
		for(AssetAlertJPA alert : asset.getAlertList()) {
			AdminAssetAlertDomain alertDomain = new AdminAssetAlertDomain();
			alertDomain.setAlertId(alert.getAlertId());
			alertDomain.setAlertMessage(alert.getAlertMessage());
			alertDomain.setAssetId(asset.getAssetId());
			
			alertDomainList.add(alertDomain);
		}
		assetDomain.setAlertDomainList(alertDomainList);
		
		List<AdminAssetScanFileDomain> scanFileDomainList = new ArrayList<AdminAssetScanFileDomain>();
		for(AssetScanFileJPA scanFile : asset.getScanFileList()) {
			
			UserDomain user = new UserDomain();
			user.setUserId(scanFile.getUser().getUserId());
			user.setUserName(scanFile.getUser().getUserName());
			
			AdminAssetScanFileDomain scanFileDomain = new AdminAssetScanFileDomain();
			scanFileDomain.setFileId(scanFile.getFileId());
			scanFileDomain.setAssetId(asset.getAssetId());
			scanFileDomain.setFileName(scanFile.getFileName());
			scanFileDomain.setUser(user);
			
			scanFileDomainList.add(scanFileDomain);
		}
		assetDomain.setScanFileDomainList(scanFileDomainList);
		
		return assetDomain;
	}
}
