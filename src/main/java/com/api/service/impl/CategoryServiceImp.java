package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Category;
import com.api.repository.CategoryRepository;
import com.api.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	
	private CategoryRepository categoryRepository;
 
	@Autowired
	public CategoryServiceImp(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	
	public List<Category> findAllCategory() {
		return (List<Category>) categoryRepository.findAll();
	}

	
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	
	public void save(Category category) {
		categoryRepository.save(category);
	}

	
	public void remove(Category category) {
		categoryRepository.delete(category);
	}

}
