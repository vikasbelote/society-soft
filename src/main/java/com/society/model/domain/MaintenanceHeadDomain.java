package com.society.model.domain;

public class MaintenanceHeadDomain {
	
	private Integer maintenanceHeadId;
	
	private String maintenanceHeadName;
	
	private String chargeType;
	
	private Integer chargeTypeId;
	
	private Double fixedAmount;
	
	private Double percentageAmount;
	
	private Integer referenceHeadId;
	
	private String referenceHeadName;
	
	private Integer societyId;
	
	private Integer calcId;

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

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getChargeTypeId() {
		return chargeTypeId;
	}

	public void setChargeTypeId(Integer chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}

	public Double getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(Double fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public Double getPercentageAmount() {
		return percentageAmount;
	}

	public void setPercentageAmount(Double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}

	public Integer getReferenceHeadId() {
		return referenceHeadId;
	}

	public void setReferenceHeadId(Integer referenceHeadId) {
		this.referenceHeadId = referenceHeadId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Integer getCalcId() {
		return calcId;
	}

	public void setCalcId(Integer calcId) {
		this.calcId = calcId;
	}

	public String getReferenceHeadName() {
		return referenceHeadName;
	}

	public void setReferenceHeadName(String referenceHeadName) {
		this.referenceHeadName = referenceHeadName;
	}
}
