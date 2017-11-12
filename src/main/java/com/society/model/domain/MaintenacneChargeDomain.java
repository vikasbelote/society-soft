package com.society.model.domain;

public class MaintenacneChargeDomain {
	
	private Integer chargeId;
	
	private Integer srNumber;
	
	private Integer maintenanceHeadId;
	
	private String maintenanceHeadName;
	
	private Double chargeValue;

	public Integer getChargeId() {
		return chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	public Integer getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(Integer srNumber) {
		this.srNumber = srNumber;
	}

	public Integer getMaintenanceHeadId() {
		return maintenanceHeadId;
	}

	public void setMaintenanceHeadId(Integer maintenanceHeadId) {
		this.maintenanceHeadId = maintenanceHeadId;
	}

	public String getMaintenanceHeadName() {
		return maintenanceHeadName;
	}

	public void setMaintenanceHeadName(String maintenanceHeadName) {
		this.maintenanceHeadName = maintenanceHeadName;
	}

	public Double getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
	}

	
}
