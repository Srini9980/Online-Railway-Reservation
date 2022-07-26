package com.passenger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passenger.exception.AuthenticationFailedException;
import com.passenger.exception.PhoneNumberAlreadyExistingException;
import com.passenger.exception.UserNameAlreadyExistingException;
import com.passenger.exception.UserNotFoundException;
import com.passenger.pojo.Passenger;
import com.passenger.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public Passenger doLogin(String userName, String password) {

		Passenger passenger = passengerRepository.login(userName, password);
		if (passenger == null) {
			throw new AuthenticationFailedException("Username or password are invalid");
		}
		return passenger;
	}

	@Override
	public Passenger savePassenger(Passenger passenger) {

		Optional<Passenger> optionalPassenger = passengerRepository.findByUserName(passenger.getUserName());
		if (optionalPassenger.isPresent()) {
			throw new UserNameAlreadyExistingException("Username already exists");
		}
		Optional<Passenger> passengerByPhone = passengerRepository.findByPhone(passenger.getPhone());
		if (passengerByPhone.isEmpty()) {
			throw new PhoneNumberAlreadyExistingException("Phone number already exists" + passenger.getPhone());
		}
		Passenger newPassenger = passengerRepository.save(passenger);
		return newPassenger;
	}

	@Override
	public List<Passenger> getAllPassenger() {

		List<Passenger> allPassenger = passengerRepository.findAll();
		return allPassenger;
	}

	@Override
	public Passenger getPassengerById(int passengerId) {

		Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
		if (optionalPassenger.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + passengerId);
		}
		Passenger passengerById = optionalPassenger.get();
		return passengerById;
	}

	@Override
	public Passenger updatePassenger(Passenger passenger) {

		Optional<Passenger> optionalPassenger = passengerRepository.findById(passenger.getPassengerId());
		if (optionalPassenger.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + passenger.getPassengerId());
		}
		Passenger updatedPassenger = passengerRepository.save(passenger);
		return updatedPassenger;
	}

	@Override
	public void deletePassengerById(int passengerId) {

		Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
		if (optionalPassenger.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + passengerId);
		}

		passengerRepository.deleteById(passengerId);
	}

	@Override
	public Optional<Passenger> getAllPassengerByUserName(String userName) {

		Optional<Passenger> passengerByUsername = passengerRepository.findByUserName(userName);
		if (passengerByUsername.isEmpty()) {
			throw new UserNotFoundException("No user found with this username : " + userName);
		}
		return passengerByUsername;
	}

	@Override
	public Optional<Passenger> getAllPassengerByPhone(long phone) {

		Optional<Passenger> passengerByPhone = passengerRepository.findByPhone(phone);
		if (passengerByPhone.isEmpty()) {
			throw new UserNotFoundException("No user found with this phone number : " + phone);
		}
		return passengerByPhone;
	}

	@Override
	public List<Passenger> getAllPassengerByLocation(String location) {

		List<Passenger> passengerByLocation = passengerRepository.findByLocation(location);
		if (passengerByLocation.isEmpty()) {
			throw new UserNotFoundException("No user found with this location : " + location);
		}
		return passengerByLocation;
	}

}
