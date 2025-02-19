package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

}
