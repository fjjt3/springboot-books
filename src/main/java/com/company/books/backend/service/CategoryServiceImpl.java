package com.company.books.backend.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		
		log.info("method searchCategories start");
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Response ok","00", "Succesfully response");
			
		}catch (Exception e) {
			response.setMetadata("Invalid response", "-1", "Unsuccesfully response");
			log.error("error consulting categories: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK) ;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> consultById(Long Id) {
		log.info("Start consultById method");
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDao.findById(Id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
			} else {
				log.error("Error consulting category");
				response.setMetadata("Invalid response", "-1", "Catergory not found");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error consulting category");
			response.setMetadata("Invalid response", "-1", "Error consulting category");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Response ok","00", "Succesfully response");
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK) ;
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> createCategory(Category category) {
		log.info("Start createCategory method");
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Category savedCategory = categoryDao.save(category);
			
			if (savedCategory != null) {
				list.add(category);
				response.getCategoryResponse().setCategory(list);
			} else {
				log.error("Error saving category");
				response.setMetadata("Invalid response", "-1", "Category not Saving");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);		
			}
			
		} catch(Exception e) {
			log.error("Error saving category");
			response.setMetadata("Invalid response", "-1", "Error saving category");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		response.setMetadata("Response ok","00", "Category created");
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}



	

}
