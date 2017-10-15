package com.society.model.domain;

public class SocietyConfigDomain {
	
	private Integer configId;
	
	private String liabilitesGeneralHeadIds;
	
	private String assetGeneralHeadIds;
	
	private Integer societyId;

	public String getLiabilitesGeneralHeadIds() {
		return liabilitesGeneralHeadIds;
	}

	public void setLiabilitesGeneralHeadIds(String liabilitesGeneralHeadIds) {
		this.liabilitesGeneralHeadIds = liabilitesGeneralHeadIds;
	}

	public String getAssetGeneralHeadIds() {
		return assetGeneralHeadIds;
	}

	public void setAssetGeneralHeadIds(String assetGeneralHeadIds) {
		this.assetGeneralHeadIds = assetGeneralHeadIds;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}
}
