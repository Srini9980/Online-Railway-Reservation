package com.train.dto;

public class Fare {

	private int fareId;
	private double tatkal;
	private double secondClass;
	private double sleeperClass;
	private double firstClass;
	private double aCChairClass;

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
		return aCChairClass;
	}

	public void setACChairClass(double aCChairClass) {
		this.aCChairClass = aCChairClass;
	}

}
