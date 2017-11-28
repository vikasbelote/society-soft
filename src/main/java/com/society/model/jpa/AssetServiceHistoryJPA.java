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
@Table(name = "sa_asset_service_history")
public class AssetServiceHistoryJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "service_history_id")
	private Integer serviceHistoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetTrackerJPA asset;
	
	@Column(name = "history_date")
	private Date historyDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private PersonJPA person;
	
	@Column(name = "additional_info")
	private String addtionalInfo;

	public Integer getServiceHistoryId() {
		return serviceHistoryId;
	}

	public void setServiceHistoryId(Integer serviceHistoryId) {
		this.serviceHistoryId = serviceHistoryId;
	}

	public AssetTrackerJPA getAsset() {
		return asset;
	}

	public void setAsset(AssetTrackerJPA asset) {
		this.asset = asset;
	}

	public Date getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}

	public PersonJPA getPerson() {
		return person;
	}

	public void setPerson(PersonJPA person) {
		this.person = person;
	}

	public String getAddtionalInfo() {
		return addtionalInfo;
	}

	public void setAddtionalInfo(String addtionalInfo) {
		this.addtionalInfo = addtionalInfo;
	}
}
