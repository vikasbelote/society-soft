package com.society.constant;

public enum EmailType {
	
	MA("MAEmail");
	
	private String value;
	
	EmailType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
