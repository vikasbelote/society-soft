package com.society.model.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PersonDomain {
	
	private Integer personId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String contactNumber;
	
	private String emailId;
	
	private String wingNumber;
	
	private String flatNumber;
	
	private String squareFeet;
	
	private Integer additionalAreaId;
	
	private String additionalAreaName;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public String getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(String squareFeet) {
		this.squareFeet = squareFeet;
	}

	public Integer getAdditionalAreaId() {
		return additionalAreaId;
	}

	public void setAdditionalAreaId(Integer additionalAreaId) {
		this.additionalAreaId = additionalAreaId;
	}

	public String getAdditionalAreaName() {
		return additionalAreaName;
	}

	public void setAdditionalAreaName(String additionalAreaName) {
		this.additionalAreaName = additionalAreaName;
	}

}
