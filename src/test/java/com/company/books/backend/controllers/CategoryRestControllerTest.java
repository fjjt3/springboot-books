package com.company.books.backend.controllers;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;

public class CategoryRestControllerTest {
	
	@InjectMocks
	CategoryRestController categoryController;
	 
	@Mock
	private ICategoryService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void createTest() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Category category = new Category(Long.valueOf(1),"Clásicos", "Libros de literatura moderna");
		
		when(service.createCategory(any(Category.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoryResponseRest> response = categoryController.createCategory(category);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
}


/*
 * @PostMapping("/categories") public ResponseEntity<CategoryResponseRest>
 * createCategory(@RequestBody Category request) {
 * 
 * ResponseEntity<CategoryResponseRest> response =
 * service.createCategory(request); return response; }
 */