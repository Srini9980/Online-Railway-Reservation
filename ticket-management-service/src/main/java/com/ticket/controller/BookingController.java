package com.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.dto.BookingResponse;
import com.ticket.pojo.Booking;
import com.ticket.repository.BookingRepository;
import com.ticket.service.BookingService;

@RestController
public class BookingController {
	
	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingRepository bookingRepository;

	@PostMapping("/booking/save")
	public BookingResponse reserveTicket(@RequestBody Booking booking) {
		
		logger.info("reserveTicket method from Booking controller is accessed");

		return bookingService.saveBooking(booking);
	}

	@DeleteMapping("/booking/delete/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") int bookingId) {
		
		logger.info("cancelBooking method from Booking controller is accessed");

		bookingService.deleteBooking(bookingId);
		return new ResponseEntity<>("Ticket cancel successfully", HttpStatus.OK);
	}

	@GetMapping("/booking/all")
	public List<Booking> fetchAllBooking() {
		
		logger.info("fetchAllBooking method from Booking controller is accessed");
		return bookingRepository.findAll();
	}
	
	@GetMapping("/booking/find/{bookingId}")
	public ResponseEntity<Booking> fetchBookingById(@PathVariable("bookingId") int bookingId) {
		
		logger.info("BookingById method from Booking controller is accessed");
		
		ResponseEntity<Booking> responseEntity = null;
		Booking booking = bookingService.getBookingById(bookingId);
		responseEntity = new ResponseEntity<>(booking, HttpStatus.OK);
		return responseEntity;
	}
	
}
