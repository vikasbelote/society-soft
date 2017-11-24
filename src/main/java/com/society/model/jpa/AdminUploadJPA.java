package com.society.model.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_file")
public class AdminUploadJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "file_id")
	private Integer fileId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_type")
	private String fileType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploaded_by_id")
	private UserJPA user;
	
	@Column(name = "uploaded_on")
	private Date uploadedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public UserJPA getUser() {
		return user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}
}
