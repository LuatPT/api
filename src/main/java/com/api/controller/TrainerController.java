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

import com.api.entity.Trainer;
import com.api.service.TrainerService;

@RestController
public class TrainerController {

	private TrainerService trainerService;

	@Autowired
	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	@RequestMapping(value = "/trainers", method = RequestMethod.GET)
	public ResponseEntity<List<Trainer>> listAllTrainer() {
		List<Trainer> listTrainer = trainerService.findAllTrainer();
		return new ResponseEntity<List<Trainer>>(listTrainer, HttpStatus.OK); 
	}

	@RequestMapping(value = "/trainers/{trainer_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> getTrainerById(@PathVariable("trainer_id") Integer trainerId) {
		Optional<Trainer> trainer = trainerService.findById(trainerId);

		if (!trainer.isPresent()) {
			return new ResponseEntity<Trainer>(trainer.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Trainer>(trainer.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/trainers", method = RequestMethod.POST)
	public ResponseEntity<Trainer> createtrainer(@RequestBody Trainer trainer, UriComponentsBuilder builder) {
		trainerService.save(trainer);
		HttpHeaders headers = new HttpHeaders();
		//Set url
		headers.setLocation(builder.path("/trainers/{trainerId}").buildAndExpand(trainer.getTrainerId()).toUri());
		return new ResponseEntity<Trainer>(trainer, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trainers/{trainer_id}", method = RequestMethod.PUT)
	public ResponseEntity<Trainer> updatetrainer(@PathVariable("trainer_id") Integer trainerId, @RequestBody Trainer trainer) {
		Optional<Trainer> currenttrainer = trainerService.findById(trainerId);

		if (!currenttrainer.isPresent()) {
			return new ResponseEntity<Trainer>(HttpStatus.NO_CONTENT);
		}

//	        currenttrainer.get().setID(trainer.getName());
//	        currenttrainer.get().setPrice(trainer.getPrice());
//	      currenttrainer.get().setDescription(trainer.getDescription());

		trainerService.save(currenttrainer.get());
		return new ResponseEntity<Trainer>(currenttrainer.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/trainers/{trainer_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Trainer> deletetrainer(@PathVariable("trainer_id") Integer trainerId) {
		Optional<Trainer> trainer = trainerService.findById(trainerId);
		if (!trainer.isPresent()) {
			return new ResponseEntity<Trainer>(HttpStatus.NOT_FOUND);
		}
		trainerService.remove(trainer.get());
		return new ResponseEntity<Trainer>(HttpStatus.NO_CONTENT);
	}
}
