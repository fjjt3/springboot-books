package com.company.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Book;
import com.company.books.backend.model.dao.IBookDao;
import com.company.books.backend.response.BookResponseRest;

public class BookServiceImpl implements IBookService{
	
	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private IBookDao bookDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BookResponseRest> searchBooks() {
		log.info("start method searchBooks()");
		
		BookResponseRest response = new BookResponseRest();
		
		try {
			
			List<Book> book = (List<Book>) bookDao.findAll();
			
			response.getBookResponse().setBook(book);
			
			response.setMetadata("Response ok", "00", "Response successfully");
			
		} catch (Exception e) {
			response.setMetadata("Response nok", "-1", "Error consulting books");
			log.error("error consulting books: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK); 
	}

	@Override
	public ResponseEntity<BookResponseRest> searchBookById(Long id) {
		
		log.info("start method searchBooks()");
		
		BookResponseRest response = new BookResponseRest();
		
		List<Book> list = new ArrayList<>();
		
		try {
			
			Optional<Book> book = bookDao.findById(id);
			
			if (book.isPresent()) {
				list.add(book.get());
				response.getBookResponse().setBook(list);
			} else {
				log.error("Error consulting books");
				response.setMetadata("Respuesta nok", "-1", "Book not found");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error en consultar libro");
			response.setMetadata("Respuesta nok", "-1", "Error consulting book");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookResponseRest> createBook(Book book) {
		log.info("start method createBook");
		
		BookResponseRest response = new BookResponseRest();
		
		List<Book> list = new ArrayList<>();
		
		try {
			
			Book bookSaved = bookDao.save(book);
			
			if( bookSaved != null) {
				list.add(bookSaved);
				response.getBookResponse().setBook(list);
			} else {
				log.error("Error saving book");
				response.setMetadata("Response nok", "-1", "Book not saved");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch( Exception e) {
			log.error("Error saving books");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar libro");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta ok", "00", "Libro creado");
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
		
	}

}
