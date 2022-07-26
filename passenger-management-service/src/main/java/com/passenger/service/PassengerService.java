package com.passenger.service;

import java.util.List;
import java.util.Optional;

import com.passenger.pojo.Passenger;

public interface PassengerService {
	
	public Passenger doLogin(String userName, String password);

	public Passenger savePassenger(Passenger passenger);

	public List<Passenger> getAllPassenger();

	public Passenger getPassengerById(int passengerId);

	public Passenger updatePassenger(Passenger passenger);

	public void deletePassengerById(int passengerId);
	
	public Optional<Passenger> getAllPassengerByUserName(String userName);
	
	public Optional<Passenger> getAllPassengerByPhone(long phone);
	
	public List<Passenger> getAllPassengerByLocation(String location);

}
