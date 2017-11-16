package com.society.model.jpa;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_mail_status")
public class EmailStatusJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "mail_status_id")
	private Integer mailStatusId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receipt_id")
	private MaintenanceReceiptJPA receipt;
	
	@Column(name = "mail_status")
	private Boolean mailStatus;
	
	@Column(name = "email_type")
	private String mailType;
	
	@Column(name = "send_date")
	private Date sendDate;

	public Integer getMailStatusId() {
		return mailStatusId;
	}

	public void setMailStatusId(Integer mailStatusId) {
		this.mailStatusId = mailStatusId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public MaintenanceReceiptJPA getReceipt() {
		return receipt;
	}

	public void setReceipt(MaintenanceReceiptJPA receipt) {
		this.receipt = receipt;
	}

	public Boolean getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(Boolean mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
