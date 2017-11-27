package com.society.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.society.model.domain.AdminUploadDomain;
import com.society.model.jpa.AdminUploadJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.UserJPA;
import com.society.repository.AdminUploadRepository;

@Service
public class AdminUploadService {
	
	private static final Logger logger = LogManager.getLogger(AdminUploadService.class);
	
	@Autowired
	private AdminUploadRepository uploadRepository;
	
	public List<AdminUploadDomain> getFile(AdminUploadDomain uploadDomain) {
		
		List<AdminUploadJPA> uploadList = uploadRepository.getCommonFile(uploadDomain);
		if(CollectionUtils.isEmpty(uploadList))
			return null;
		
		List<AdminUploadDomain> uploadDomainList = new ArrayList<AdminUploadDomain>();
		
		for(AdminUploadJPA upload : uploadList) {
			AdminUploadDomain tempUploadDomain = new AdminUploadDomain();
			tempUploadDomain.setFileId(upload.getFileId());
			tempUploadDomain.setFileName(upload.getFileName());
			tempUploadDomain.setFileType(upload.getFileType());
			tempUploadDomain.setUploadedDate(upload.getUploadedDate());
			tempUploadDomain.setUserId(upload.getUser().getUserId());
			tempUploadDomain.setUserName(upload.getUser().getUserName());
			
			uploadDomainList.add(tempUploadDomain);
		}
		
		return uploadDomainList;
	}
	
	
	public List<AdminUploadDomain> getFileTypeSpecificFile(List<AdminUploadDomain> uploadFileList, String fileType) {
	
		List<AdminUploadDomain> uploadDomainList = new ArrayList<AdminUploadDomain>();
		for(AdminUploadDomain upload : uploadFileList) {
			if(fileType.equals(upload.getFileType()))
				uploadDomainList.add(upload);
		}
		return uploadDomainList;
	}
	
	public boolean uploadFile(String rootPath, MultipartFile file, AdminUploadDomain uploadDomain) {
		
		String directoryName = rootPath.concat(uploadDomain.getSocietyName());
		File directory = new File(directoryName);
	    if (!directory.exists()) {
	    	logger.info("Directory does not exist");
	        directory.mkdir();
	        logger.info("Directory get created : " + directoryName);
	    }
	    try {
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get(directoryName + "/" + file.getOriginalFilename());
	        uploadDomain.setFileName(file.getOriginalFilename());
	        logger.info("writing file content");
	        Files.write(path, bytes);
	        logger.info("successfuly write file content");
	        return true;
	    }
	    catch (IOException e){
	    	logger.debug("error in uploading file : " + e.getMessage());
	    	return false;
	    }
	}
	
	public boolean saveUploadFileDetails(AdminUploadDomain uploadDomain) {
		
		UserJPA user = new UserJPA();
		user.setUserId(uploadDomain.getUserId());
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(uploadDomain.getSocietyId());
		
		AdminUploadJPA upload = new AdminUploadJPA();
		upload.setFileName(uploadDomain.getFileName());
		upload.setFileType(uploadDomain.getFileType());
		upload.setUser(user);
		upload.setSociety(society);
		
		Date currentDate  = new Date();
		java.sql.Date uploadedDate = new java.sql.Date(currentDate.getTime());
		upload.setUploadedDate(uploadedDate);
		
		return uploadRepository.saveUploadFileDetails(upload);
	}
	
	public void getUploadFile(AdminUploadDomain uploadDomain) {
		
		AdminUploadJPA upload = uploadRepository.getFile(uploadDomain);
		if(upload != null)
			uploadDomain.setFileName(upload.getFileName());
	}
	
	public boolean deleteFile(AdminUploadDomain uploadDomain) {
		return uploadRepository.deleteObjectById(AdminUploadJPA.class, uploadDomain.getFileId());
	}
	
}
