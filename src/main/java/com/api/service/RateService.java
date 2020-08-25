package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Rate;


public interface RateService{
	
	List<Rate> findAllRate(); 
	
    Optional<Rate> findById(Integer id);
    
    void save(Rate rate);
    
    void remove(Rate rate);

}