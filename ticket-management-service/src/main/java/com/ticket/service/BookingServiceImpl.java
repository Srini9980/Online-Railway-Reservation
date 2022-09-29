package com.ticket.service;

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

import com.ticket.dto.BookingResponse;
import com.ticket.dto.Train;
import com.ticket.exception.BookingNotFoundException;
import com.ticket.pojo.Booking;
import com.ticket.pojo.DatabaseSequence;
import com.ticket.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public BookingResponse saveBooking(Booking booking) {

		BookingResponse bookingResponse = new BookingResponse();
		booking.setBookingId(getSequenceNumber(Booking.SEQUENCE_NAME));
		Random random = new Random();
		booking.setPnrId(random.nextInt(99999999)+100000);
		Booking newBooking = bookingRepository.save(booking);

		Train train = restTemplate.getForObject("http://TRAIN-MANAGEMENT-SERVICE/train/find/" + booking.getTrainId(),
				Train.class);
//		if (newBooking.getTrainId() != train.getTrainId()) {
//			throw new BookingNotFoundException("No train found with this Id : " + train.getTrainId());
//		}
		bookingResponse.setBooking(newBooking);
		bookingResponse.setTrain(train);
		return bookingResponse;

	}

	@Override
	public void deleteBooking(int bookingId) {

		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new BookingNotFoundException("Booking not found with Id: " + bookingId);
		}
		bookingRepository.deleteById(bookingId);

	}

	@Override
	public Booking getBookingById(int bookingId) {

		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new BookingNotFoundException("No booking found with this Id : " + bookingId);
		}

		Booking bookingById = optionalBooking.get();
		return bookingById;
	}

	@Override
	public List<Booking> getAllBooking() {

		List<Booking> allBooking = bookingRepository.findAll();
		return allBooking;
	}

	@Override
	public int getSequenceNumber(String sequenceName) {
		
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seq", 1);
		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter)? counter.getSeq():1;
	}

}
