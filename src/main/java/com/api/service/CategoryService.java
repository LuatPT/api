package com.api.service;

import java.util.List;

import com.api.entity.Category;


public interface CategoryService{
	
	List<Category> findAllCategory(); 
    
    Category findById(Integer id);
    
    void save(Category category);
    
    void remove(Category category);

}