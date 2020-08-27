package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Product;
import com.api.repository.ProductRepository;
import com.api.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
	
	private ProductRepository productRepository;
 
	@Autowired
	public ProductServiceImp(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void remove(Product product) {
		productRepository.delete(product);
	}


}
