package com.society.model.domain;

import java.util.List;

public class MaintenanceReceiptDomain {
	
	private Integer memberId;
	
	private String memberName;
	
	private String billNumber;
	
	private List<MaintenacneChargeDomain> chargeList;

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
}
