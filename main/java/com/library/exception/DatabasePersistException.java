package com.library.exception;

public class DatabasePersistException extends Exception {

	private int statusCode;

	public DatabasePersistException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

}
