package com.library.service;

import java.util.List;

import com.library.dto.BookCheckoutResponse;
import com.library.dto.BookDto;
import com.library.dto.ReturningBookResponse;
import com.library.exception.CustomException;
import com.library.exception.DatabasePersistException;

public interface LibraryService {

	BookDto addBook(BookDto book) throws DatabasePersistException;

	List<BookDto> findAllBook() throws DatabasePersistException;

	List<BookDto> findBookByBookName(String bookName) throws DatabasePersistException;

	List<BookDto> findBookByAuthor(String author) throws DatabasePersistException;

	BookCheckoutResponse checkoutBook(String referenceId) throws CustomException, DatabasePersistException;
   
	ReturningBookResponse returnBook(String referenceId) throws CustomException, DatabasePersistException;


}
