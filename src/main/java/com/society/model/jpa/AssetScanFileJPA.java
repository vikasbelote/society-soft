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
@Table(name = "sa_asset_scan_file")
public class AssetScanFileJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "file_id")
	private Integer fileId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetTrackerJPA asset;
	
	@Column(name = "file_name")
	private String fileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploaded_by")
	private UserJPA user;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public AssetTrackerJPA getAsset() {
		return asset;
	}

	public void setAsset(AssetTrackerJPA asset) {
		this.asset = asset;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UserJPA getUser() {
		return user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}
}
