package com.company.books.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.service.IBookService;

@RestController
@RequestMapping("/v1")
public class BookRestController {
	
	private IBookService service;
	
	@GetMapping("/books")
	public ResponseEntity<BookResponseRest> consultBook(){
		
		ResponseEntity<BookResponseRest> response = service.searchBooks();
		return response;
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<BookResponseRest> searchBookById(@PathVariable Long id){
		
		ResponseEntity<BookResponseRest> response = service.searchBookById(id);
		return response;
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookResponseRest> saveBook(@RequestBody Book request){
		
		ResponseEntity<BookResponseRest> response = service.createBook(request);
		return response;
	}
	
	
	

}
