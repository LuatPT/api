package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Cart;


public interface CartService{
	
	List<Cart> findAllCart(); 
	
    Optional<Cart> findById(Integer id);
    
    void save(Cart cart);
    
    void remove(Cart cart);

}