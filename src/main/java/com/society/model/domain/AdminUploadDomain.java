package com.society.model.domain;

import java.sql.Date;

public class AdminUploadDomain {
	
	private Integer fileId;
	
	private String fileName;
	
	private String fileType;
	
	private Integer userId;
	
	private String userName;
	
	private Date uploadedDate;
	
	private Integer societyId;
	
	private String societyName;
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

}
