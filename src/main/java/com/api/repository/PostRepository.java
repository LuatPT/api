package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
