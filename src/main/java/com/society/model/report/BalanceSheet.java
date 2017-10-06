package com.society.model.report;

public class BalanceSheet {
	
	private SectionReportModel liabilities;
	
	private SectionReportModel assets;
	
	private String asOnDate;

	public SectionReportModel getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(SectionReportModel liabilities) {
		this.liabilities = liabilities;
	}

	public SectionReportModel getAssets() {
		return assets;
	}

	public void setAssets(SectionReportModel assets) {
		this.assets = assets;
	}

	public String getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
}
