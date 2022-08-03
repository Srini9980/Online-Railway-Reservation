package com.train.dto;

import com.train.pojo.Train;

public class ResponseTemplate {

	private Train train;
	private Fare fare;

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Fare getFare() {
		return fare;
	}

	public void setFare(Fare fare) {
		this.fare = fare;
	}

}
