package com.library.contoller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.library.dto.BookCheckoutResponse;
import com.library.dto.BookDto;
import com.library.dto.CheckoutRequest;
import com.library.dto.ReturningBookResponse;
import com.library.exception.CustomException;
import com.library.exception.DatabasePersistException;
import com.library.service.LibraryService;

@RestController
@RequestMapping("/api")
public class LibraryController {
	private static final Logger LOG = LoggerFactory.getLogger(LibraryController.class);

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private Gson gson;

	@PostMapping("/addBook")
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto book) throws DatabasePersistException {
		BookDto result = libraryService.addBook(book);
		LOG.info("Added Book : {}", gson.toJson(result));
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping("/catelogue")
	public ResponseEntity<List<BookDto>> displayCatelogue() throws DatabasePersistException {
		List<BookDto> books = libraryService.findAllBook();
		LOG.info("Fetched the Library's catelogue : {}", books);
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}

	@GetMapping("/search")
	public ResponseEntity<List<BookDto>> searchBooks(
			@RequestParam(required = false) String bookName,
			@RequestParam(required = false) String author)
			throws CustomException, DatabasePersistException {

		if (StringUtils.isBlank(author) && StringUtils.isBlank(bookName)) {
			LOG.error("Both AUTHOR & BOOKNAME is blank in query params");
			throw new CustomException("Either you need query with bookName or author", 601);
		}

		if (StringUtils.isNotBlank(author) && StringUtils.isNotBlank(bookName)) {
			LOG.error("Search option is available with either bookname or author");
			throw new CustomException("Search option is available with either bookname or author", 600);
		}

		List<BookDto> books;
		if (StringUtils.isNotBlank(author)) {
			books = libraryService.findBookByAuthor(author);
			LOG.info("Books Fetched by author : {}", gson.toJson(books));
		} else {
			books = libraryService.findBookByBookName(bookName);
			LOG.info("Books Fetched by its name : {}", gson.toJson(books));
		}

		return ResponseEntity.status(HttpStatus.OK).body(books);
	}

	@PostMapping("/checkout")
	public ResponseEntity<BookCheckoutResponse> checkOut(@RequestBody CheckoutRequest checkoutRequest)
			throws CustomException, DatabasePersistException {
		BookCheckoutResponse response = null;

		if (StringUtils.isBlank(checkoutRequest.getReferenceId())) {
			throw new CustomException("referenceId is mandatory", 610);
		}

		LOG.info("Incoming request for checking out : {}", checkoutRequest.getReferenceId());
		response = libraryService.checkoutBook(checkoutRequest.getReferenceId());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PostMapping("/return")
	public ResponseEntity<ReturningBookResponse> returnBook(@RequestBody CheckoutRequest request)
			throws CustomException, DatabasePersistException {

		ReturningBookResponse response = null;
		if (StringUtils.isBlank(request.getReferenceId())) {
			throw new CustomException("referenceId is mandatory", 611);
		}

		LOG.info("Incoming request for returning book : {}", request.getReferenceId());
		response = libraryService.returnBook(request.getReferenceId());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

}
