package com.society.model.domain;

public class MaintenacneChargeDomain {
	
	private Integer srNumber;
	
	private Integer generalHeadId;
	
	private String generalHeadName;
	
	private Double chargeValue;

	public Integer getGeneralHeadId() {
		return generalHeadId;
	}

	public void setGeneralHeadId(Integer generalHeadId) {
		this.generalHeadId = generalHeadId;
	}

	public Double getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
	}

	public String getGeneralHeadName() {
		return generalHeadName;
	}

	public void setGeneralHeadName(String generalHeadName) {
		this.generalHeadName = generalHeadName;
	}

	public Integer getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(Integer srNumber) {
		this.srNumber = srNumber;
	}
}
