package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Post;


public interface PostService{
	
	List<Post> findAllPost(); 
	
    Optional<Post> findById(Integer id);
    
    void save(Post post);
    
    void remove(Post post);

}