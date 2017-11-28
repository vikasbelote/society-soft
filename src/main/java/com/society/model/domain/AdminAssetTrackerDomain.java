package com.society.model.domain;

import java.sql.Date;
import java.util.List;

public class AdminAssetTrackerDomain {
	
	private Integer assetId;
	
	private Integer societyId;
	
	private String assetName;
	
	private String assetTagNumber;
	
	private String vendorName;
	
	private Integer categoryId;
	
	private String categoryName;
	
	private String assetLocation;
	
	private Date purchaseDate;
	
	private Double assetCost;
	
	private String assetStatus;
	
	private List<AdminAssertContactDomain> contactDomainList;
	
	private List<AdminAssetServiceHistoryDomain> serviceHistoryDomainList;
	
	private List<AdminAssetAlertDomain> alertDomainList;
	
	private List<AdminAssetScanFileDomain> scanFileDomainList;

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetTagNumber() {
		return assetTagNumber;
	}

	public void setAssetTagNumber(String assetTagNumber) {
		this.assetTagNumber = assetTagNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getAssetCost() {
		return assetCost;
	}

	public void setAssetCost(Double assetCost) {
		this.assetCost = assetCost;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public List<AdminAssertContactDomain> getContactDomainList() {
		return contactDomainList;
	}

	public void setContactDomainList(List<AdminAssertContactDomain> contactDomainList) {
		this.contactDomainList = contactDomainList;
	}

	public List<AdminAssetServiceHistoryDomain> getServiceHistoryDomainList() {
		return serviceHistoryDomainList;
	}

	public void setServiceHistoryDomainList(List<AdminAssetServiceHistoryDomain> serviceHistoryDomainList) {
		this.serviceHistoryDomainList = serviceHistoryDomainList;
	}

	public List<AdminAssetAlertDomain> getAlertDomainList() {
		return alertDomainList;
	}

	public void setAlertDomainList(List<AdminAssetAlertDomain> alertDomainList) {
		this.alertDomainList = alertDomainList;
	}

	public List<AdminAssetScanFileDomain> getScanFileDomainList() {
		return scanFileDomainList;
	}

	public void setScanFileDomainList(List<AdminAssetScanFileDomain> scanFileDomainList) {
		this.scanFileDomainList = scanFileDomainList;
	}

}
