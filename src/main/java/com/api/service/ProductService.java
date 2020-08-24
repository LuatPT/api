package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Product;


public interface ProductService{
	
	List<Product> findAllProduct(); 
	
    Optional<Product> findById(Integer id);
    
    void save(Product product);
    
    void remove(Product product);

}