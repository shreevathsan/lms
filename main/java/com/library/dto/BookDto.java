package com.library.dto;

import javax.validation.constraints.NotBlank;

public class BookDto {

	@NotBlank(message = "bookName is mandatory")
	private String bookName;
	@NotBlank(message = "author is mandatory")
	private String author;
	@NotBlank(message = "genre is mandatory")
	private String genre;
	private boolean availabilityStatus;
	private String referenceId;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

}
