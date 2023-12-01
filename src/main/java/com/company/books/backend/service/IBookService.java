package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;

public interface IBookService {
	
	public ResponseEntity<BookResponseRest> searchBooks();
	public ResponseEntity<BookResponseRest> searchBookById(Long id);
	public ResponseEntity<BookResponseRest> createBook(Book book);

}
