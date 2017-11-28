package com.society.model.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sa_asset_tracker")
public class AssetTrackerJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "asset_id")
	private Integer assetId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@Column(name = "asset_name")
	private String assetName;
	
	@Column(name = "asset_tag_number")
	private String assetTagNumber;
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private AssetCategoryJPA category;
	
	@Column(name = "asset_location")
	private String assetLocation;
	
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	@Column(name = "asset_cost")
	private Double assetCost;
	
	@Column(name = "asset_status")
	private String assetStatus;
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AssetContactJPA> contactList;
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AssetServiceHistoryJPA> serviceHistoryList;
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AssetAlertJPA> alertList;
	
	@OneToMany(mappedBy = "asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AssetScanFileJPA> scanFileList;

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
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

	public AssetCategoryJPA getCategory() {
		return category;
	}

	public void setCategory(AssetCategoryJPA category) {
		this.category = category;
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

	public List<AssetContactJPA> getContactList() {
		return contactList;
	}

	public void setContactList(List<AssetContactJPA> contactList) {
		this.contactList = contactList;
	}

	public List<AssetServiceHistoryJPA> getServiceHistoryList() {
		return serviceHistoryList;
	}

	public void setServiceHistoryList(List<AssetServiceHistoryJPA> serviceHistoryList) {
		this.serviceHistoryList = serviceHistoryList;
	}

	public List<AssetAlertJPA> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<AssetAlertJPA> alertList) {
		this.alertList = alertList;
	}

	public List<AssetScanFileJPA> getScanFileList() {
		return scanFileList;
	}

	public void setScanFileList(List<AssetScanFileJPA> scanFileList) {
		this.scanFileList = scanFileList;
	}
}
