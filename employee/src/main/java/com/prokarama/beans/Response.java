package com.prokarama.beans;

public class Response<T> {

	private String responseCode;
	private String responseMessage;
	private T jsonObject;
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}
	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	/**
	 * @return the jsonObject
	 */
	public T getJsonObject() {
		return jsonObject;
	}
	/**
	 * @param jsonObject the jsonObject to set
	 */
	public void setJsonObject(T jsonObject) {
		this.jsonObject = jsonObject;
	}
	
}
