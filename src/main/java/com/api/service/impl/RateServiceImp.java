package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Rate;
import com.api.repository.RateRepository;
import com.api.service.RateService;

@Service
public class RateServiceImp implements RateService {
	
	private RateRepository rateRepository;
 
	@Autowired
	public RateServiceImp(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public List<Rate> findAllRate() {
		return (List<Rate>) rateRepository.findAll();
	}

	@Override
	public Optional<Rate> findById(Integer id) {
		return rateRepository.findById(id);
	}

	@Override
	public void save(Rate rate) {
		rateRepository.save(rate);
	}

	@Override
	public void remove(Rate rate) {
		rateRepository.delete(rate);
	}


}
