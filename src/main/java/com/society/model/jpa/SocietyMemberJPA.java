package com.society.model.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_society_member")
public class SocietyMemberJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Integer memberId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private PersonJPA person;
	
	@Column(name = "wing_number")
	private String wingNumber;
	
	@Column(name = "flat_number")
	private String flatNumber;
	
	@Column(name = "square_feet")
	private Integer squareFeet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "additional_area_id")
	private AdditionalAreaJPA additionalArea;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public PersonJPA getPerson() {
		return person;
	}

	public void setPerson(PersonJPA person) {
		this.person = person;
	}

	public String getWingNumber() {
		return wingNumber;
	}

	public void setWingNumber(String wingNumber) {
		this.wingNumber = wingNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public Integer getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(Integer squareFeet) {
		this.squareFeet = squareFeet;
	}

	public AdditionalAreaJPA getAdditionalArea() {
		return additionalArea;
	}

	public void setAdditionalArea(AdditionalAreaJPA additionalArea) {
		this.additionalArea = additionalArea;
	}

}
