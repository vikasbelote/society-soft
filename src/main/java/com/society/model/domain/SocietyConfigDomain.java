package com.society.model.domain;

import java.sql.Date;

public class SocietyConfigDomain {
	
	private Integer configId;
	
	private String liabilitesGeneralHeadIds;
	
	private String liabilitesOrderIds;
	
	private String assetGeneralHeadIds;
	
	private String assetOrderIds;
	
	private String expenseGeneralHeadIds;
	
	private String expenseOrderIds;
	
	private String incomeGeneralHeadIds;
	
	private String incomeOrderIds;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer maintenanceCycle;
	
	private Integer societyId;
	
	private Integer maintenancePaymentInterestRate;
	
	private String maintenancePaymentChequeName;

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

	public String getLiabilitesOrderIds() {
		return liabilitesOrderIds;
	}

	public void setLiabilitesOrderIds(String liabilitesOrderIds) {
		this.liabilitesOrderIds = liabilitesOrderIds;
	}

	public String getAssetOrderIds() {
		return assetOrderIds;
	}

	public void setAssetOrderIds(String assetOrderIds) {
		this.assetOrderIds = assetOrderIds;
	}

	public String getExpenseGeneralHeadIds() {
		return expenseGeneralHeadIds;
	}

	public void setExpenseGeneralHeadIds(String expenseGeneralHeadIds) {
		this.expenseGeneralHeadIds = expenseGeneralHeadIds;
	}

	public String getExpenseOrderIds() {
		return expenseOrderIds;
	}

	public void setExpenseOrderIds(String expenseOrderIds) {
		this.expenseOrderIds = expenseOrderIds;
	}

	public String getIncomeGeneralHeadIds() {
		return incomeGeneralHeadIds;
	}

	public void setIncomeGeneralHeadIds(String incomeGeneralHeadIds) {
		this.incomeGeneralHeadIds = incomeGeneralHeadIds;
	}

	public String getIncomeOrderIds() {
		return incomeOrderIds;
	}

	public void setIncomeOrderIds(String incomeOrderIds) {
		this.incomeOrderIds = incomeOrderIds;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaintenanceCycle() {
		return maintenanceCycle;
	}

	public void setMaintenanceCycle(Integer maintenanceCycle) {
		this.maintenanceCycle = maintenanceCycle;
	}

	public Integer getMaintenancePaymentInterestRate() {
		return maintenancePaymentInterestRate;
	}

	public void setMaintenancePaymentInterestRate(
			Integer maintenancePaymentInterestRate) {
		this.maintenancePaymentInterestRate = maintenancePaymentInterestRate;
	}

	public String getMaintenancePaymentChequeName() {
		return maintenancePaymentChequeName;
	}

	public void setMaintenancePaymentChequeName(String maintenancePaymentChequeName) {
		this.maintenancePaymentChequeName = maintenancePaymentChequeName;
	}
}
