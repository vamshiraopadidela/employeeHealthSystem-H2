package com.prokarma.errorcodes;

public enum ErrorMessages {

Forbidden("Request resource is forbidden"),NotFound("Request resource not found"),EmptyRequest("Not able to process EmptyRequest"),SUCCESS("Request has been processed"),
BeanException("Unable to copy request to employeedetails"),HibernateException("Unable to process the request"),InvalidDate("Invalid Date format"),NoRecordsFound("No Records found"),
BadRequest("Request validation Failed");
	private final String value;

	ErrorMessages(String value) {
	this.value=value;
}

public String getValue() {
    return value;
}

}
