package com.library.dto;

import lombok.Data;

@Data
public class ReturningBookResponse {

	private String message;
	private Integer fineAmount;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Integer fineAmount) {
		this.fineAmount = fineAmount;
	}

}
