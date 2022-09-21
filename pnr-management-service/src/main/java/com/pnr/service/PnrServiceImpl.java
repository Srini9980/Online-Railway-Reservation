package com.pnr.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pnr.dto.Booking;
import com.pnr.dto.PnrResponse;
import com.pnr.dto.Train;
import com.pnr.exception.PnrIdAlreadyExistsException;
import com.pnr.exception.PnrNotFoundException;
//import com.pnr.pojo.DatabaseSequence;
import com.pnr.pojo.Pnr;
import com.pnr.repository.PnrRepository;

@Service
public class PnrServiceImpl implements PnrService {

	@Autowired
	private PnrRepository pnrRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Pnr savePnr(Pnr pnr) {

		Optional<Pnr> pnrById = pnrRepository.findById(pnr.getPnrId());
		Random random = new Random();
		pnr.setPnrId(random.nextInt(99999999)+100000);
		if (pnrById.isPresent()) {
			throw new PnrIdAlreadyExistsException("PNR number already exists ");
		}
//		pnr.setPnrId(getSequenceNumber(Pnr.SEQUENCE_NAME));
		Pnr newPnr = pnrRepository.save(pnr);
		return newPnr;
	}

	@Override
	public PnrResponse getPnrByBookingId(int pnrId) {

		PnrResponse pnrResponse = new PnrResponse();
		Optional<Pnr> optionalPnr = pnrRepository.findById(pnrId);
		if (optionalPnr.isEmpty()) {
			throw new PnrNotFoundException("No pnr available with this Id : " + pnrId);
		}

		Pnr pnr = optionalPnr.get();
		Train train = restTemplate.getForObject("http://TRAIN-MANAGEMENT-SERVICE/train/find/" + pnr.getTrainId(), Train.class);
//		if (train.getTrainId() != pnr.getTrainId()) {
//			throw new PnrNotFoundException("No train booking done on this Id");
//		}

		Booking booking = restTemplate.getForObject("http://TICKET-MANAGEMENT-SERVICE/booking/find/" + pnr.getBookingId(), Booking.class);
//		if (booking.getBookingId() != pnr.getBookingId()) {
//			throw new PnrNotFoundException("No booking done on this Id");
//		}
		pnrResponse.setBooking(booking);
		pnrResponse.setTrain(train);
		pnrResponse.setPnr(pnr);
		return pnrResponse;
	}

	@Override
	public List<Pnr> getAllPnr() {
		
		List<Pnr> allPnr = pnrRepository.findAll();
		return allPnr;
	}

	@Override
	public void deletePnr(int pnrId) {
	
		Optional<Pnr> optionalPnr = pnrRepository.findById(pnrId);
		if(optionalPnr.isEmpty()) {
			throw new PnrNotFoundException("No pnr available with this Id : " + pnrId);
		}
		
		pnrRepository.deleteById(pnrId);
	}

//	@Override
//	public int getSequenceNumber(String sequenceName) {
//		
//		Query query = new Query(Criteria.where("id").is(sequenceName));
//		Update update = new Update().inc("seq", 1);
//		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DatabaseSequence.class);
//		return !Objects.isNull(counter)? counter.getSeq():1;
//	}

}
