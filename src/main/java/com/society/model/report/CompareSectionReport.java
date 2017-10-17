package com.society.model.report;

public class CompareSectionReport {
	
	private SectionReportModel leftSection;
	
	private SectionReportModel rightSection;
	
	private String asOnDate;

	public SectionReportModel getLeftSection() {
		return leftSection;
	}

	public void setLeftSection(SectionReportModel leftSection) {
		this.leftSection = leftSection;
	}

	public SectionReportModel getRightSection() {
		return rightSection;
	}

	public void setRightSection(SectionReportModel rightSection) {
		this.rightSection = rightSection;
	}

	public String getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	
}
