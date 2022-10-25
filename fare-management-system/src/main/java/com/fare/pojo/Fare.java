package com.fare.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fare_db")
public class Fare {

	@Id
	private int fareId;
	@NotNull(message = "Tatkal is price is required")
	@Positive(message = "Price should not be in negative")
	private double tatkal;
	@NotNull(message = "Second class is price is required")
	@Positive(message = "Price should not be in negative")
	private double secondClass;
	@NotNull(message = "Sleeper class is price is required")
	@Positive(message = "Price should not be in negative")
	private double sleeperClass;
	@NotNull(message = "First class is price is required")
	@Positive(message = "Price should not be in negative")
	private double firstClass;
	@NotNull(message = "Ac chair class is price is required")
	@Positive(message = "Price should not be in negative")
	private double ACChairClass;

	public int getFareId() {
		return fareId;
	}

	public void setFareId(int fareId) {
		this.fareId = fareId;
	}

	public double getTatkal() {
		return tatkal;
	}

	public void setTatkal(double tatkal) {
		this.tatkal = tatkal;
	}

	public double getSecondClass() {
		return secondClass;
	}

	public void setSecondClass(double secondClass) {
		this.secondClass = secondClass;
	}

	public double getSleeperClass() {
		return sleeperClass;
	}

	public void setSleeperClass(double sleeperClass) {
		this.sleeperClass = sleeperClass;
	}

	public double getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(double firstClass) {
		this.firstClass = firstClass;
	}

	public double getACChairClass() {
		return ACChairClass;
	}

	public void setACChairClass(double aCChairClass) {
		ACChairClass = aCChairClass;
	}

}
