package com.SpringShop.service.api;

import com.SpringShop.entity.web.Category;

public interface CategoryService {

	Iterable<Category> findAll();
	
	Category findOne(Integer id);
	
	long countAll();

	Category save(Category category);
	
	void delete(Integer id);
	
}
