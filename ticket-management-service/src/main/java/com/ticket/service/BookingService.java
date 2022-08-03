package com.ticket.service;

import java.util.List;

import com.ticket.dto.BookingResponse;
import com.ticket.dto.Passenger;
import com.ticket.pojo.Booking;

public interface BookingService {

	public BookingResponse saveBooking(Booking booking);
	
//	public Booking saveBooking(int trainId, List<Passenger> passenger);

	public void deleteBooking(int bookingId);


}
