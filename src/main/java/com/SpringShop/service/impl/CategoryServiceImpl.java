package com.SpringShop.service.impl;

import com.SpringShop.entity.web.Category;
import com.SpringShop.repository.CategoryRepository;
import com.SpringShop.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return categoryRepository.count();
	}

	@Override
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		categoryRepository.delete(id);
	}

}
