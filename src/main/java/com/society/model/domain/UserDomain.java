package com.society.model.domain;

public class UserDomain {

	private Integer userId;
	
	private String userName;
	
	private String userPassword;
	
	private RoleDomain role;
	
	private SocietyDomain society;
	
	private PersonDomain person;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public SocietyDomain getSociety() {
		return society;
	}

	public void setSociety(SocietyDomain society) {
		this.society = society;
	}

	public PersonDomain getPerson() {
		return person;
	}

	public void setPerson(PersonDomain person) {
		this.person = person;
	}

	public RoleDomain getRole() {
		return role;
	}

	public void setRole(RoleDomain role) {
		this.role = role;
	}
}
