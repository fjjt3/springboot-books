package com.company.books.backend.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public CategoryResponseRest searchCategories() {
		
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
		}
		return response;
	}

}
