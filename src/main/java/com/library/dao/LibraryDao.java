package com.library.dao;

import java.util.List;

import com.library.entity.Book;
import com.library.exception.DatabasePersistException;

public interface LibraryDao {

	Book saveBook(Book book) throws DatabasePersistException;
	
	Book findByReferenceId(String referenceId) throws DatabasePersistException;

	List<Book> findAllBooks() throws DatabasePersistException;

	List<Book> findBookByBookName(String bookName) throws DatabasePersistException;

	List<Book> findBookByAuthor(String author) throws DatabasePersistException;
}
