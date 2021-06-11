package com.prokarma.errorcodes;

public enum ModuleConstants {

	EMPLOYEE_INSURANC_REQUEST("EMPLOYEE_INSURANC_REQUEST"),ISSUED("ISSUED"),DELIVERED("DELIVERED"),UNDELIVERED("UNDELIVERED"),
	BROKER_INSURANC_REQUEST("BROKER_INSURANC_REQUEST"),BROKER_DEACTIVATE_REQUEST("BROKER_DEACTIVATE_REQUEST");
	private final String value;

	ModuleConstants(String value) {
		this.value=value;
	}

	public String getValue() {
	    return value;
	}
}
