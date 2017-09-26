package com.society.model.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SocietyForm {
	
	private String societyName;
	
	private String userName;
	
	private String userPassword;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String contactNumber;
	
	private String emailId;
	
	private String addressText;
	
	private String areaName;
	
	private String plotNo;

	private String sectorNo;

	private String city;
	
	private String pinCode;

	private String state;
	
	private String[] memberFirstNameArr;
	
	private String[] memberMiddleNameArr;
	
	private String[] memberLastNameArr;
	
	private String[] memberContactNumberArr;
	
	private String[] memberEmailIdArr;

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getSectorNo() {
		return sectorNo;
	}

	public void setSectorNo(String sectorNo) {
		this.sectorNo = sectorNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getMemberFirstNameArr() {
		return memberFirstNameArr;
	}

	public void setMemberFirstNameArr(String[] memberFirstNameArr) {
		this.memberFirstNameArr = memberFirstNameArr;
	}

	public String[] getMemberMiddleNameArr() {
		return memberMiddleNameArr;
	}

	public void setMemberMiddleNameArr(String[] memberMiddleNameArr) {
		this.memberMiddleNameArr = memberMiddleNameArr;
	}

	public String[] getMemberLastNameArr() {
		return memberLastNameArr;
	}

	public void setMemberLastNameArr(String[] memberLastNameArr) {
		this.memberLastNameArr = memberLastNameArr;
	}

	public String[] getMemberContactNumberArr() {
		return memberContactNumberArr;
	}

	public void setMemberContactNumberArr(String[] memberContactNumberArr) {
		this.memberContactNumberArr = memberContactNumberArr;
	}

	public String[] getMemberEmailIdArr() {
		return memberEmailIdArr;
	}

	public void setMemberEmailIdArr(String[] memberEmailIdArr) {
		this.memberEmailIdArr = memberEmailIdArr;
	}
}