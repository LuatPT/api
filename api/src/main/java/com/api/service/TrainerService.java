package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.entity.Trainer;


public interface TrainerService{
	
	List<Trainer> findAllTrainer(); 
	
    Optional<Trainer> findById(Integer id);
    
    void save(Trainer trainer);
    
    void remove(Trainer trainer);

}