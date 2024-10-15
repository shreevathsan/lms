package com.library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
	@SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "author")
	private String author;

	@Column(name = "genre")
	private String genre;

	@Column(name = "reference_id")
	private String referenceId;

	@Column(name = "availability_status")
	private boolean available;

	@Column(name = "checkout_date")
	private Date checkOutDate;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "fine_per_date")
	private Integer finePerDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getReferernceId() {
		return referenceId;
	}

	public void setReferernceId(String referernceId) {
		this.referenceId = referernceId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getFinePerDate() {
		return finePerDate;
	}

	public void setFinePerDate(Integer finePerDate) {
		this.finePerDate = finePerDate;
	}

}
