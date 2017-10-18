package com.society.constant;

public enum SectionEnum {
	
	LC("LIABILITIES AND CAPITAL"),
	PA("PROPERTIES AND ASSETS"),
	EXPENSES("EXPENSES"),
	INCOME("INCOME"),
	MA("MAINTENANCE");
	
	private String value;
	
	SectionEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
