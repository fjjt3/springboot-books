package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> searchCategories();
	public ResponseEntity<CategoryResponseRest> consultById(Long id);
	public ResponseEntity<CategoryResponseRest> createCategory(Category category);
	public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);
}
