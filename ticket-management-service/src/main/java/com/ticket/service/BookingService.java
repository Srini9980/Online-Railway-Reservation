package com.ticket.service;

import java.util.List;

import com.ticket.dto.BookingResponse;
import com.ticket.pojo.Booking;

public interface BookingService {

	public BookingResponse saveBooking(Booking booking);
	
	public Booking getBookingById(int bookingId);

	public void deleteBooking(int bookingId);
	
	public List<Booking> getAllBooking();

}
