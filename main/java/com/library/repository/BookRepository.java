package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByReferenceId(String referenceId);
	
	List<Book> findByBookName(String bookName);
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByBookNameAndAuthor(String bookName, String author);
	
	

}
