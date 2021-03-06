package com.society.constant;

public enum ReportEnum {
	
	BS("Balance Sheet"), IE("Income & Expense"), MA("Maintenance");
	
	private String value;
	
	ReportEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

}
