package com.library.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.exception.DatabasePersistException;
import com.library.repository.BookRepository;

@Service
public class LibraryDaoImpl implements LibraryDao {

	private static final Logger LOG = LoggerFactory.getLogger(LibraryDaoImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book saveBook(Book book) throws DatabasePersistException {
		Book result = null;
		try {
			result = bookRepository.save(book);
		} catch (Exception e) {
			LOG.error("Error while saving book : {}", e);
			throw new DatabasePersistException(602,
					"Something went wrong while saving book, please contact the support team");
		}
		return result;
	}

	@Override
	public List<Book> findAllBooks() throws DatabasePersistException {
		List<Book> results = null;
		try {
			results = bookRepository.findAll();
		} catch (Exception e) {
			LOG.error("Error while fetching books : {}", e);
			throw new DatabasePersistException(603,
					"Something went wrong while fetching catelogue, please contact the support team");
		}
		return results;
	}

	@Override
	public List<Book> findBookByBookName(String bookName) throws DatabasePersistException {
		List<Book> results = null;
		try {
			results = bookRepository.findByBookName(bookName);
		} catch (Exception e) {
			LOG.error("Error while fetching books : {}", e);
			throw new DatabasePersistException(604,
					"Something went wrong while fetching catelogue, please contact the support team");
		}
		return results;
	}

	@Override
	public List<Book> findBookByAuthor(String author) throws DatabasePersistException {
		List<Book> results = null;
		try {
			results = bookRepository.findByAuthor(author);
		} catch (Exception e) {
			LOG.error("Error while fetching books with author : {}", e);
			throw new DatabasePersistException(605,
					"Something went wrong while fetching book with author, please contact the support team");
		}

		return results;
	}

	@Override
	public Book findByReferenceId(String referenceId) throws DatabasePersistException {
		Book book = null;
		try {
			book = bookRepository.findByReferenceId(referenceId);
		} catch (Exception e) {
			LOG.error("Error while fetching books with referenceId : {}", e);
			throw new DatabasePersistException(606,
					"Something went wrong while fetching book with referenceId, please contact the support team");
		}
		return book;
	}

}
