package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_member_out_amount")
public class MemberOutAmountJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "out_id")
	private Integer outId;
	
	@Column(name = "out_amount")
	private Double outAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_id")
	private MaintenanceCycleJPA cycle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private SocietyMemberJPA member;

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Double getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
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
}
