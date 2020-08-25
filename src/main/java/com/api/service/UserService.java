package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.User;


public interface UserService{
	
	List<User> findAllUser(); 
	
    Optional<User> findById(Integer id);
    
    void save(User user);
    
    void remove(User user);

}