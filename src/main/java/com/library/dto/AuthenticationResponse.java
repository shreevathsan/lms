package com.library.dto;

public class AuthenticationResponse {

	private String jwtToken;

	public AuthenticationResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

}
