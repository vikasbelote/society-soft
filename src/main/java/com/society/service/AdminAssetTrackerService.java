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

import com.society.model.domain.AdminAssetTrackerDomain;
import com.society.model.jpa.AssetCategoryJPA;
import com.society.model.jpa.AssetContactJPA;
import com.society.model.jpa.AssetScanFileJPA;
import com.society.model.jpa.AssetServiceHistoryJPA;
import com.society.model.jpa.AssetTrackerJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.UserJPA;
import com.society.repository.AdminAssetTrackerRepository;
import com.society.utils.SocietyUtils;

@Service
public class AdminAssetTrackerService {
	
	private static final Logger logger = LogManager.getLogger(AdminAssetTrackerService.class);
	
	@Autowired
	private AdminAssetTrackerRepository adminAssetTrackerRepository;
	
	public boolean saveAssetTrackerEntry(AdminAssetTrackerDomain adminAssetTrackerDomain, MultipartFile[] files) {
		
		AssetCategoryJPA category = new AssetCategoryJPA();
		category.setCategoryId(SocietyUtils.setNullIfZero(adminAssetTrackerDomain.getCategoryId()));
		
		AssetTrackerJPA asset = new AssetTrackerJPA();
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
			
			for(MultipartFile file : files) {
		        AssetScanFileJPA scanFile = new AssetScanFileJPA();
				scanFile.setAsset(asset);
				scanFile.setUser(user);
				scanFile.setFileName(file.getOriginalFilename());
				try {
			        byte[] bytes = file.getBytes();
			        Path path = Paths.get(directoryName + "/" + file.getOriginalFilename());
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
}
