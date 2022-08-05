package com.ticket.service;

import com.ticket.dto.BookingResponse;
import com.ticket.pojo.Booking;

public interface BookingService {

	public BookingResponse saveBooking(Booking booking);
	
	public Booking getBookingById(int bookingId);

	public void deleteBooking(int bookingId);


}
