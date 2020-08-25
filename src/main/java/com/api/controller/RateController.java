package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.entity.Rate;
import com.api.service.RateService;

@RestController
@RequestMapping("/v1")
public class RateController {

	private RateService rateService;

	@Autowired
	public RateController(RateService rateService) {
		this.rateService = rateService;
	}

	@RequestMapping(value = "/rates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rate>> listAllRate() {
		List<Rate> listRate = rateService.findAllRate();
		return new ResponseEntity<List<Rate>>(listRate, HttpStatus.OK);
	}

	@RequestMapping(value = "/rates/{rate_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rate> getProductById(@PathVariable("rate_id") Integer rateId) {
		Optional<Rate> rate = rateService.findById(rateId);

		if (!rate.isPresent()) {
			return new ResponseEntity<Rate>(rate.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Rate>(rate.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/rates", method = RequestMethod.POST)
	public ResponseEntity<Rate> createRate(@RequestBody Rate rate, UriComponentsBuilder builder) {
		rateService.save(rate);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/rates/{rateId}").buildAndExpand(rate.getRateId()).toUri());
		return new ResponseEntity<Rate>(rate, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/rates/{rate_id}", method = RequestMethod.PUT)
	public ResponseEntity<Rate> updateRate(@PathVariable("rate_id") Integer rateId,
			@RequestBody Rate rate) {
		Optional<Rate> currentRate = rateService.findById(rateId);

		if (!currentRate.isPresent()) {
			return new ResponseEntity<Rate>(HttpStatus.NO_CONTENT);
		}

		currentRate.get().setRateId(rate.getRateId());
		currentRate.get().setUserId(rate.getUserId());
		currentRate.get().setProductId(rate.getProductId());
		currentRate.get().setRate(rate.getRate());
		currentRate.get().setComment(rate.getComment());
		currentRate.get().setImage(rate.getImage());
		currentRate.get().setCreateAt(rate.getCreateAt());
		
		rateService.save(currentRate.get());
		return new ResponseEntity<Rate>(currentRate.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/rates/{rate_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Rate> deleteRate(@PathVariable("rate_id") Integer rateId) {
		Optional<Rate> rate = rateService.findById(rateId);
		if (!rate.isPresent()) {
			return new ResponseEntity<Rate>(HttpStatus.NOT_FOUND);
		}
		rateService.remove(rate.get());
		return new ResponseEntity<Rate>(HttpStatus.NO_CONTENT);
	}
}
