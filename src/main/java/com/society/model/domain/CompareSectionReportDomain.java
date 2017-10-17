package com.society.model.domain;

import java.sql.Date;

public class CompareSectionReportDomain {
	
	private Date currentYearStartDate;
	
	private Date currentYearEndDate;
	
	private Date lastYearStartDate;
	
	private Date lastYearEndDate;
	
	private Integer societyId;
	
	private String reportName;
	
	private String leftSectionName;
	
	private String rightSectionName;

	public Date getCurrentYearStartDate() {
		return currentYearStartDate;
	}

	public void setCurrentYearStartDate(Date currentYearStartDate) {
		this.currentYearStartDate = currentYearStartDate;
	}

	public Date getCurrentYearEndDate() {
		return currentYearEndDate;
	}

	public void setCurrentYearEndDate(Date currentYearEndDate) {
		this.currentYearEndDate = currentYearEndDate;
	}

	public Date getLastYearStartDate() {
		return lastYearStartDate;
	}

	public void setLastYearStartDate(Date lastYearStartDate) {
		this.lastYearStartDate = lastYearStartDate;
	}

	public Date getLastYearEndDate() {
		return lastYearEndDate;
	}

	public void setLastYearEndDate(Date lastYearEndDate) {
		this.lastYearEndDate = lastYearEndDate;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getLeftSectionName() {
		return leftSectionName;
	}

	public void setLeftSectionName(String leftSectionName) {
		this.leftSectionName = leftSectionName;
	}

	public String getRightSectionName() {
		return rightSectionName;
	}

	public void setRightSectionName(String rightSectionName) {
		this.rightSectionName = rightSectionName;
	}

}
