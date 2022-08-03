package com.ticket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticket.dto.BookingResponse;
import com.ticket.dto.Passenger;
import com.ticket.dto.Train;
import com.ticket.exception.BookingNotFoundException;
import com.ticket.pojo.Booking;
import com.ticket.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RestTemplate restTemplate;

//	@Override
//	public Booking saveBooking(int trainId, List<Passenger> passenger) {
//
//		Booking booking = new Booking();
//		passenger.forEach(n -> {
//			n.setBooking(booking);
//		});
//		Passenger newPassenger = restTemplate
//				.getForObject("http://localhost:8082/passenger/find/" + booking.getPassengerId(), Passenger.class);
//		if (booking.getPassengerId() != newPassenger.getPassengerId()) {
//			throw new BookingNotFoundException("Login to reserve the ticket");
//		}
//		
//		Train bookTrain = restTemplate.getForObject("http;//localhost:8083/train/find/" + booking.getTrainId(),
//				Train.class);
//		if (booking.getTrainId() != bookTrain.getTrainId()) {
//			throw new BookingNotFoundException(
//					"Something went wrong, no train available with this Id" + bookTrain.getTrainId());
//		}
//		BookingResponse bookingResponse = new BookingResponse();
//		bookingResponse.setPassenger(newPassenger);
//		bookingResponse.setTrain(bookTrain);
//
//		Booking newbooking = bookingRepository.save(booking);
//		return newbooking;
//	}

	@Override
	public BookingResponse saveBooking(Booking booking) {
		
		BookingResponse bookingResponse = new BookingResponse();
		Booking newBooking = bookingRepository.save(booking);
		
		Train train = restTemplate.getForObject("http://localhost:8083/train/find/" + booking.getTrainId(), Train.class);
		if(newBooking.getTrainId() != train.getTrainId()) {
			throw new BookingNotFoundException("No train found with this Id : " + train.getTrainId());
		}
		
		Passenger passenger = restTemplate.getForObject("http://localhost:8082/passenger/find/" + booking.getPassengerId(), Passenger.class);
		if(newBooking.getPassengerId() != passenger.getPassengerId()) {
			throw new BookingNotFoundException("Login in to book the reserve the ticket");
		}
		
		bookingResponse.setPassenger(passenger);
		bookingResponse.setTrain(train);
		bookingResponse.setBooking(newBooking);
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

}
