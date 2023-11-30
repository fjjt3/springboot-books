package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> consultCategory() {
		
		ResponseEntity<CategoryResponseRest>  response = service.searchCategories();
		return response;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> consultById(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.consultById(id);
		return response;
	}
	

}
