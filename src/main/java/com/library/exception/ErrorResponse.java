package com.library.exception;

public class ErrorResponse {

	private int statusCode;
	private String details;

	public ErrorResponse(int statusCode, String details) {
		this.statusCode = statusCode;
		this.details = details;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getDetails() {
		return details;
	}

}
