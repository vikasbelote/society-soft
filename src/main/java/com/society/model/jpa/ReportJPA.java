package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sa_report")
public class ReportJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "report_id")
	private Integer reportId;
	
	@Column(name = "report_name")
	private String reportName;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
