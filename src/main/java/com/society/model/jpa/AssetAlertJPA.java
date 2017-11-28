package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_asset_alert")
public class AssetAlertJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "alert_id")
	private Integer alertId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetTrackerJPA asset;
	
	@Column(name = "alert_message")
	private String alertMessage;

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public AssetTrackerJPA getAsset() {
		return asset;
	}

	public void setAsset(AssetTrackerJPA asset) {
		this.asset = asset;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
}
