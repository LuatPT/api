package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {

}
