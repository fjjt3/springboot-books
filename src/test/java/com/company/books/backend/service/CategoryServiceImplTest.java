package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;

public class CategoryServiceImplTest {
	
	@InjectMocks
	CategoryServiceImpl service;
	
	@Mock
	ICategoryDao categoryDao;
	
	List<Category> list = new ArrayList<Category>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.loadCategories();
	}
	
	@Test
	public void searchCategoriesTest() {
		when(categoryDao.findAll()).thenReturn(list);
		
		ResponseEntity<CategoryResponseRest> response =  service.searchCategories();
		
		assertEquals(2, response.getBody().getCategoryResponse().getCategory().size());
		
		verify(categoryDao, times(1)).findAll();
	}
	

	public void loadCategories() {
		
		Category catOne = new Category(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
		Category catTwo = new Category(Long.valueOf(1), "Lacteos", "Distintos Lacteos");
		
		list.add(catOne);
		list.add(catTwo);
	}
}
