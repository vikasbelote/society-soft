package com.society.constant;

public enum TypeEnum {
	ADD("ADD"),
	SUBTRACT("SUBTRACT"),
	DISPLAY("DISPLAY");
	
	private String value;
	
	TypeEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
