package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
