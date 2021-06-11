package com.prokarma.errorcodes;

public enum ErrorCodes {
Forbidden("403"),NotFound("404"),EmptyRequest("211"),SUCCESS("0000"),BeanException("2890")
,HibernateException("2891"),InvalidDate("2892"),NoRecordsFound("2893"),BadRequest("400");
	private final String value;

ErrorCodes(String value) {
	this.value=value;
}

public String getValue() {
    return value;
}
}
