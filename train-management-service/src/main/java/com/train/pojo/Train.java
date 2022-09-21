package com.train.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "train_collection")
public class Train {

	@Transient
	public static final String SEQUENCE_NAME = "train_sequence";

	@Id
	private int trainId;
	@NotNull(message = "Please enter the train name")
	private String trainName;
	@NotNull(message = "Source is required")
	private String source;
	@NotNull(message = "Train Destination is required")
	private String destination;
	@NotNull(message = "Train dparture n=time is required")
	private String departureTime;
	@NotNull(message = "Train arrival time is required")
	private String arrivalTime;
	@NotNull(message = "No of seats availability is required")
	private int seatsAvailability;
	@NotNull(message = "Please provide the fare id")
	@Positive(message = "Id should be in positive number")
	private int fareId;

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeatsAvailability() {
		return seatsAvailability;
	}

	public void setSeatsAvailability(int seatsAvailability) {
		this.seatsAvailability = seatsAvailability;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFareId() {
		return fareId;
	}

	public void setFareId(int fareId) {
		this.fareId = fareId;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

}
