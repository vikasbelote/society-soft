package com.society.model.report;

public class TransactionReportModel {
	
	private String description;
	
	private Double currentYearAmount;
	
	private Double lastYearAmount;
	
	private String currentYearType;
	
	private String lastYearType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCurrentYearAmount() {
		return currentYearAmount;
	}

	public void setCurrentYearAmount(Double currentYearAmount) {
		this.currentYearAmount = currentYearAmount;
	}

	public Double getLastYearAmount() {
		return lastYearAmount;
	}

	public void setLastYearAmount(Double lastYearAmount) {
		this.lastYearAmount = lastYearAmount;
	}

	public String getCurrentYearType() {
		return currentYearType;
	}

	public void setCurrentYearType(String currentYearType) {
		this.currentYearType = currentYearType;
	}

	public String getLastYearType() {
		return lastYearType;
	}

	public void setLastYearType(String lastYearType) {
		this.lastYearType = lastYearType;
	}

	
}
