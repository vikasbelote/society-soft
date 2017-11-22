package com.society.model.domain;

public class SocietyMemberDomain {
	
	private Integer memberId;
	
	private Integer personId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String emailId;
	
	private String wingNumber;
	
	private String flatNumber;
	
	private Integer squareFeet;
	
	private Integer additionalAreaId;
	
	private Integer societyId;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public Integer getAdditionalAreaId() {
		return additionalAreaId;
	}

	public void setAdditionalAreaId(Integer additionalAreaId) {
		this.additionalAreaId = additionalAreaId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
}
