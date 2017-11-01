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
}
