package com.api.service;

import java.util.List;

import com.api.entity.Product;


public interface ProductService{
	
	List<Product> findAllProduct(); 
	
    Product findById(Integer id);
    
    void save(Product product);
    
    void remove(Product product);

}