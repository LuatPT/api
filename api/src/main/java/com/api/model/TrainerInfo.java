package com.api.model;

public class TrainerInfo  {
	private int trainerId;
	
	private String trainerName;
	
	private int experience;
	
	private String trainerAvatar;
	
	private String trainerQuote;
	
	private String trainerCost;

	public TrainerInfo() {
		
	}

	public TrainerInfo(int trainerId, String trainerName, int experience, String trainerAvatar, String trainerQuote,
			String trainerCost) {
		super();
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.experience = experience;
		this.trainerAvatar = trainerAvatar;
		this.trainerQuote = trainerQuote;
		this.trainerCost = trainerCost;
	}

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
