package com.society.model.report;

import java.util.List;

public class SectionReportModel {
	
	private String sectionName;
	
	private String currentYear;
	
	private String prevYear;
	
	private List<GeneralHeadReportModel> generalHeadList;
	
	private Double grossTotalCurrentYear;
	
	private Double grossTotalPrevYear;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

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
}
