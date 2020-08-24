package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
