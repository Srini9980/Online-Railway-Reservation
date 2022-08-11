package com.fare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fare.exception.FareIdAlreadyExistsException;
import com.fare.exception.FareNotFoundException;
import com.fare.pojo.Fare;
import com.fare.repository.FareRepository;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	private FareRepository fareRepository;

	@Override
	public Fare saveFare(Fare fare) {

		Optional<Fare> optionalFetchById = fareRepository.findById(fare.getFareId());
		if (optionalFetchById.isPresent()) {
			throw new FareIdAlreadyExistsException("Fare id already exists : " + fare.getFareId());
		}
		Fare newFare = fareRepository.save(fare);
		return newFare;
	}

	@Override
	public Fare getFareById(int fareId) {

		Optional<Fare> optionalFetchById = fareRepository.findById(fareId);
		if (optionalFetchById.isEmpty()) {
			throw new FareNotFoundException("No fare deatils found with this Id : " + fareId);
		}
		return optionalFetchById.get();

	}

	@Override
	public Fare modifyFare(Fare fare) {

		Optional<Fare> optionalModifyById = fareRepository.findById(fare.getFareId());
		if (optionalModifyById.isPresent()) {
			return fareRepository.save(fare);
		} else {
			throw new FareNotFoundException("No fare deatils found with this Id : " + fare.getFareId());
		}
	}

	@Override
	public void deleteFare(int fareId) {

		Optional<Fare> optionalFare = fareRepository.findById(fareId);
		if (optionalFare.isPresent()) {
			fareRepository.deleteById(fareId);
		} else {
			throw new FareNotFoundException("No fare deatils found with this Id : " + fareId);
		}

	}

}
