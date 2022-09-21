package com.fare.service;

import java.util.List;

import com.fare.pojo.Fare;

public interface FareService {
	
	public Fare saveFare(Fare fare);
	
	public Fare getFareById(int fareId);
	
	public Fare modifyFare(Fare fare);
	
	public void deleteFare(int fareId);
	
	public List<Fare> getAllFare();
	
	public int getSequenceNumber(String sequenceName);

}
