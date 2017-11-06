package com.society.model.domain;

import java.sql.Date;

public class EmailStatusDomain {
	
	private Integer mailStatusId;

	private Integer societyId;

	private Integer receiptId;
	
	private Boolean mailStatus;
	
	private String mailType;
	
	private Date sendDate;
	
	private Integer memberId;
	
	private String memberName;
	
	private String billNumber;

	public Integer getMailStatusId() {
		return mailStatusId;
	}

	public void setMailStatusId(Integer mailStatusId) {
		this.mailStatusId = mailStatusId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
}
