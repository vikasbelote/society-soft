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
@Table(name = "sa_parking_manager")
public class ParakingManagerJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "parking_id")
	private Integer parkingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@Column(name = "slot_no")
	private String slotNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private SocietyMemberJPA member;
	
	@Column(name = "allotment_status")
	private String allotmentStatus;

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public SocietyMemberJPA getMember() {
		return member;
	}

	public void setMember(SocietyMemberJPA member) {
		this.member = member;
	}

	public String getAllotmentStatus() {
		return allotmentStatus;
	}

	public void setAllotmentStatus(String allotmentStatus) {
		this.allotmentStatus = allotmentStatus;
	}
}
