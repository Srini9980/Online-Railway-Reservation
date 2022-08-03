package com.fare.service;

import com.fare.pojo.Fare;

public interface FareService {
	
	public Fare saveFare(Fare fare);
	
	public Fare getFare(int fareId);
	
	public Fare modifyFare(Fare fare);
	
	public void deleteFare(int fareId);

}
