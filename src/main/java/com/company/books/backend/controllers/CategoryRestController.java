package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
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
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> createCategory(@RequestBody Category request) {
		
		ResponseEntity<CategoryResponseRest> response = service.createCategory(request);
		return response;
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category request, @PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.updateCategory(request, id);
		return response;
		
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.deleteCategory(id);
		return response;
		
	}
	
	

}
