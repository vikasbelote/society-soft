package com.society.model.report;

import java.util.List;

public class GeneralHeadReportModel {
	
	private String generalHeadName;
	
	private List<TransactionReportModel> transactionList;
	
	private Double totalCurrentYearGeneralHeadAmount;
	
	private Double totalLastYearGeneralHeadAmount;

	public String getGeneralHeadName() {
		return generalHeadName;
	}

	public void setGeneralHeadName(String generalHeadName) {
		this.generalHeadName = generalHeadName;
	}

	public List<TransactionReportModel> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionReportModel> transactionList) {
		this.transactionList = transactionList;
	}

	public Double getTotalCurrentYearGeneralHeadAmount() {
		return totalCurrentYearGeneralHeadAmount;
	}

	public void setTotalCurrentYearGeneralHeadAmount(
			Double totalCurrentYearGeneralHeadAmount) {
		this.totalCurrentYearGeneralHeadAmount = totalCurrentYearGeneralHeadAmount;
	}

	public Double getTotalLastYearGeneralHeadAmount() {
		return totalLastYearGeneralHeadAmount;
	}

	public void setTotalLastYearGeneralHeadAmount(
			Double totalLastYearGeneralHeadAmount) {
		this.totalLastYearGeneralHeadAmount = totalLastYearGeneralHeadAmount;
	}

	
	
}
