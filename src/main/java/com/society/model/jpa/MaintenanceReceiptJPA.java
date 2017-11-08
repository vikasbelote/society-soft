package com.society.model.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Entity
@Table(name = "sa_maintenance_receipt")
public class MaintenanceReceiptJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "receipt_id")
	private Integer receipId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cycle_id")
	private MaintenanceCycleJPA cycle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private SocietyMemberJPA member;
	
	@Column(name = "bill_number")
	private String billNumber;
	
	@OneToMany(mappedBy="receipt", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MaintenanceChargeJPA> chargeList;
	
	@Override
	public boolean equals(Object otherReceipt){
		if(otherReceipt == null)
			return false;
		
		if(!(otherReceipt instanceof MaintenanceReceiptJPA))
			return false;
		
		MaintenanceReceiptJPA otherObj = (MaintenanceReceiptJPA)otherReceipt;
		if(this.receipId == otherObj.receipId)
			return true;
		else
			return false;
	}
	
	@PostPersist
	public void updareBillNumber() {
		billNumber = "B-" + this.receipId;
	}
	
	public MaintenanceCycleJPA getCycle() {
		return cycle;
	}

	public void setCycle(MaintenanceCycleJPA cycle) {
		this.cycle = cycle;
	}

	public SocietyMemberJPA getMember() {
		return member;
	}

	public void setMember(SocietyMemberJPA member) {
		this.member = member;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Integer getReceipId() {
		return receipId;
	}

	public void setReceipId(Integer receipId) {
		this.receipId = receipId;
	}

	public List<MaintenanceChargeJPA> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<MaintenanceChargeJPA> chargeList) {
		this.chargeList = chargeList;
	}

}
