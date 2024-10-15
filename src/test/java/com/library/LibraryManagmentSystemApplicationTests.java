package com.library;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.dao.LibraryDao;
import com.library.service.LibraryService;

@SpringBootTest
class LibraryManagmentSystemApplicationTests {

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private LibraryDao libraryDao;

	@Test
	void contextLoads() {
	}

	@Test
	void testLibraryServiceBeanNotNull() {
		assertNotNull(libraryService);
	}

	@Test
	void testLibraryDaoNotNull() {
		assertNotNull(libraryDao);
	}

}
