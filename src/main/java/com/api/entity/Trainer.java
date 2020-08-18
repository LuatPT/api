package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "trainers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "trainer_id")
	private int trainerId;
	
	@Column(name = "trainer_name")
	private String trainerName;
	
	@Column(name = "experience")
	private int experience;
	
	@Column(name = "trainer_avatar")
	private String trainerAvatar;
	
	@Column(name = "trainer_quote")
	private String trainerQuote;
	
	@Column(name = "trainer_cost")
	private String trainerCost;

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getTrainerAvatar() {
		return trainerAvatar;
	}

	public void setTrainerAvatar(String trainerAvatar) {
		this.trainerAvatar = trainerAvatar;
	}

	public String getTrainerQuote() {
		return trainerQuote;
	}

	public void setTrainerQuote(String trainerQuote) {
		this.trainerQuote = trainerQuote;
	}

	public String getTrainerCost() {
		return trainerCost;
	} 

	public void setTrainerCost(String trainerCost) {
		this.trainerCost = trainerCost;
	}
	
	
}
