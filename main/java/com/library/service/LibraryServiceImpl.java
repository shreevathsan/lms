package com.library.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.library.dao.LibraryDao;
import com.library.dto.BookCheckoutResponse;
import com.library.dto.BookDto;
import com.library.dto.ReturningBookResponse;
import com.library.entity.Book;
import com.library.exception.CustomException;
import com.library.exception.DatabasePersistException;
import com.library.util.CommonUtils;

@Service
public class LibraryServiceImpl implements LibraryService {

	private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);

	@Autowired
	private LibraryDao libraryDao;

	@Autowired
	private Gson gson;

	@Override
	public BookDto addBook(BookDto book) throws DatabasePersistException {
		Book bookEntity = CommonUtils.convertToEntity(book);
		return CommonUtils.convertToDto(libraryDao.saveBook(bookEntity));
	}

	@Override
	public List<BookDto> findAllBook() throws DatabasePersistException {
		return CommonUtils.convertToDtos(libraryDao.findAllBooks());
	}

	@Override
	public List<BookDto> findBookByBookName(String bookName) throws DatabasePersistException {
		return CommonUtils.convertToDtos(libraryDao.findBookByBookName(bookName));
	}

	@Override
	public List<BookDto> findBookByAuthor(String author) throws DatabasePersistException {
		return CommonUtils.convertToDtos(libraryDao.findBookByAuthor(author));
	}

	@Override
	public BookCheckoutResponse checkoutBook(String referenceId) throws CustomException, DatabasePersistException {
		Book book = libraryDao.findByReferenceId(referenceId);
		if (null == book) {
			LOG.error("Invalid referenceId");
			throw new CustomException("Invalid book", 607);
		} else if (!book.isAvailable()) {
			LOG.error("Book already checked out");
			throw new CustomException("Book is not available", 608);
		}

		book.setCheckOutDate(new Date());
		book.setAvailable(false);
		book.setReturnDate(CommonUtils.getDueDateForBook());
		libraryDao.saveBook(book);

		BookCheckoutResponse response = getCheckoutResponse(book);
		LOG.info("Checkout Response : {}", gson.toJson(response));

		return response;

	}

	public ReturningBookResponse returnBook(String referenceId) throws CustomException, DatabasePersistException {
		Book book = libraryDao.findByReferenceId(referenceId);

		if (null == book) {
			LOG.error("Invalid referenceId");
			throw new CustomException("Invalid book", 607);
		} else if (book.isAvailable()) {
			LOG.error("Book already returned");
			throw new CustomException("Book already returned", 608);
		}

		ReturningBookResponse returningBookResponse = new ReturningBookResponse();
		returningBookResponse.setMessage("Book returned successfully");
		returningBookResponse.setFineAmount(
				CommonUtils.calculateFine(CommonUtils.dateToString(book.getReturnDate()), book.getFinePerDate()));

		book.setReturnDate(null);
		book.setAvailable(true);
		book.setCheckOutDate(null);

		libraryDao.saveBook(book);

		return returningBookResponse;
	}

	private BookCheckoutResponse getCheckoutResponse(Book book) {
		BookCheckoutResponse response = new BookCheckoutResponse();
		response.setAuthor(book.getAuthor());
		response.setBookName(book.getBookName());
		response.setCheckoutDate(CommonUtils.dateToString(book.getCheckOutDate()));
		response.setDueDate(CommonUtils.dateToString(book.getReturnDate()));
		response.setReferenceId(book.getReferernceId());

		return response;
	}

}
