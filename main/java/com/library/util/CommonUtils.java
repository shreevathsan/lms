package com.library.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.exception.CustomException;

public class CommonUtils {

	private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

	public static String getReferenceId() {
		return UUID.randomUUID().toString();
	}

	public static Date getDueDateForBook() throws CustomException {

		try {
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_MONTH, 10);
			return calendar.getTime();
		} catch (Exception e) {
			LOG.error("Error while parsing date : {}", e);
			throw new CustomException("Something went wrong, please try again later", 609);
		}
	}

	public static int calculateFine(String dueDateStr, int finePerDay) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);

		LocalDate currentDate = LocalDate.now();

		if (currentDate.isAfter(dueDate)) {
			long daysLate = ChronoUnit.DAYS.between(dueDate, currentDate);
			return (int) (daysLate * finePerDay);
		}

		return 0;
	}

	public static String dateToString(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = localDate.format(formatter);
		return formattedDate;
	}

	public static Book convertToEntity(BookDto bookDto) {
		Book book = new Book();

		book.setAuthor(bookDto.getAuthor());
		book.setAvailable(true);
		book.setFinePerDate(10);
		book.setGenre(bookDto.getGenre());
		book.setBookName(bookDto.getBookName());
		book.setReferernceId(getReferenceId());

		return book;
	}

	public static List<BookDto> convertToDtos(List<Book> books) {
		List<BookDto> bookDtos = new ArrayList<>();
		for (Book book : books) {
			bookDtos.add(convertToDto(book));
		}
		return bookDtos;
	}

	public static BookDto convertToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor(book.getAuthor());
		bookDto.setAvailabilityStatus(book.isAvailable());
		bookDto.setBookName(book.getBookName());
		bookDto.setGenre(book.getGenre());
		bookDto.setReferenceId(book.getReferernceId());

		return bookDto;
	}

}
