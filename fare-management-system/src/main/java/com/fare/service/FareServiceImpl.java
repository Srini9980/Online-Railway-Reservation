package com.fare.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fare.exception.FareIdAlreadyExistsException;
import com.fare.exception.FareNotFoundException;
import com.fare.pojo.Fare;
import com.fare.repository.FareRepository;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	private FareRepository fareRepository;

	@Autowired
	private MongoOperations mongoOperations;

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

//	@Override
//	public int getSequenceNumber(String sequenceName) {
//
//		Query query = new Query(Criteria.where("id").is(sequenceName));
//		Update update = new Update().inc("seq", 1);
//		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
//				DatabaseSequence.class);
//		return !Objects.isNull(counter) ? counter.getSeq() : 1;
//	}

	@Override
	public List<Fare> getAllFare() {
		
		List<Fare> allFare = fareRepository.findAll();
		return allFare;
	}

}
