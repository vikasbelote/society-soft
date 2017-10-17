package com.society.model.report;

import java.util.List;

public class SectionReportModel {
	
	private String currentYear;
	
	private String prevYear;
	
	private List<GeneralHeadReportModel> generalHeadList;
	
	private Double grossTotalCurrentYear;
	
	private Double grossTotalPrevYear;

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getPrevYear() {
		return prevYear;
	}

	public void setPrevYear(String prevYear) {
		this.prevYear = prevYear;
	}

	public List<GeneralHeadReportModel> getGeneralHeadList() {
		return generalHeadList;
	}

	public void setGeneralHeadList(List<GeneralHeadReportModel> generalHeadList) {
		this.generalHeadList = generalHeadList;
	}

	public Double getGrossTotalCurrentYear() {
		return grossTotalCurrentYear;
	}

	public void setGrossTotalCurrentYear(Double grossTotalCurrentYear) {
		this.grossTotalCurrentYear = grossTotalCurrentYear;
	}

	public Double getGrossTotalPrevYear() {
		return grossTotalPrevYear;
	}

	public void setGrossTotalPrevYear(Double grossTotalPrevYear) {
		this.grossTotalPrevYear = grossTotalPrevYear;
	}
}
