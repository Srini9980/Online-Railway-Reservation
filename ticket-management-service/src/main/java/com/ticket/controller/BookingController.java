package com.ticket.controller;

import java.util.List;

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
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
//	@PostMapping("/booking/save")
//	public ResponseEntity<Object> reserveTicket(@RequestBody BookingRequest bookingRequest) {
//		
//	List<Passenger> passengers = bookingRequest.getPassenger();
//		int passengerId = bookingRequest.getPassengerId();
//		
//		passengers.forEach(r -> {
//			r.getUserName();
//			r.getAge();
//			r.getGender();
//		});
//		
//		Booking newBooking = bookingService.saveBooking(passengerId, passengers);
//		return new ResponseEntity<>(newBooking, HttpStatus.OK);
//	}
	
	@PostMapping("/booking/save")
	public BookingResponse reserveTicket(@RequestBody Booking booking) {

		return bookingService.saveBooking(booking);
	}
	
	@DeleteMapping("/booking/delete/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") int bookingId) {
		
		bookingService.deleteBooking(bookingId);
		return new ResponseEntity<>("Ticket cancel successfully", HttpStatus.OK);
	}
	
	@GetMapping("/booking/all")
	public List<Booking> fetchAllBooking() {
		return bookingRepository.findAll();
	}
}
