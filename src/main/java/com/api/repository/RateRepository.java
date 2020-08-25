package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Rate;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

}
