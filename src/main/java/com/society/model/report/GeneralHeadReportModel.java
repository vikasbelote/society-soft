package com.society.model.report;

import java.util.List;

public class GeneralHeadReportModel {
	
	private String generalHeadName;
	
	private List<TransactionReportModel> transactionList;
	
	private Double totalGeneralHeadAmount;

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

	public Double getTotalGeneralHeadAmount() {
		return totalGeneralHeadAmount;
	}

	public void setTotalGeneralHeadAmount(Double totalGeneralHeadAmount) {
		this.totalGeneralHeadAmount = totalGeneralHeadAmount;
	}
}
