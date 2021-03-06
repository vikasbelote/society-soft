package com.society.model.domain;

import java.sql.Date;

public class AdminAssetServiceHistoryDomain {
	
	private Integer serviceHistoryId;
	
	private Integer assetId;
	
	private Date historyDate;

	private PersonDomain person;
	
	private String addtionalInfo;
	
	private Boolean isDeleted;
	
	private Boolean isUpdated;

	public Integer getServiceHistoryId() {
		return serviceHistoryId;
	}

	public void setServiceHistoryId(Integer serviceHistoryId) {
		this.serviceHistoryId = serviceHistoryId;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Date getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}

	public PersonDomain getPerson() {
		return person;
	}

	public void setPerson(PersonDomain person) {
		this.person = person;
	}

	public String getAddtionalInfo() {
		return addtionalInfo;
	}

	public void setAddtionalInfo(String addtionalInfo) {
		this.addtionalInfo = addtionalInfo;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
