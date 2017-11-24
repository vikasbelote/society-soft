package com.society.constant;

public enum FileType {
	
	COMMFILE("COMMFILE"),FLFILE("FLFILE"),RPT("RPT");
	
	private String value;
	
	FileType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
