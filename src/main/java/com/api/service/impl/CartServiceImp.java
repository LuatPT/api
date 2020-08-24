package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Cart;
import com.api.repository.CartRepository;
import com.api.service.CartService;

@Service
public class CartServiceImp implements CartService {
	
	private CartRepository cartRepository;
 
	@Autowired
	public CartServiceImp(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public List<Cart> findAllCart() {
		return (List<Cart>) cartRepository.findAll();
	}

	@Override
	public Optional<Cart> findById(Integer id) {
		return cartRepository.findById(id);
	}

	@Override
	public void save(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void remove(Cart cart) {
		cartRepository.delete(cart);
	}


}
