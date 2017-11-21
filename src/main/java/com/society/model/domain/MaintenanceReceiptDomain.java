package com.society.model.domain;

import java.util.List;

public class MaintenanceReceiptDomain {
	
	private Integer receiptId;
	
	private Integer memberId;
	
	private String memberName;
	
	private String billNumber;
	
	private List<MaintenacneChargeDomain> chargeList;
	
	private Double totalValue;
	
	private String emailId;
	
	private Integer mobileNumber;
	
	private Boolean billStatus;
	
	private String flatNumber;
	
	private Double outstandingAmount;
	
	private Boolean isActive;
	
	private Double paidAmount;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public List<MaintenacneChargeDomain> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<MaintenacneChargeDomain> chargeList) {
		this.chargeList = chargeList;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Boolean billStatus) {
		this.billStatus = billStatus;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
}
