package com.ticket.dto;

import com.ticket.pojo.Booking;

public class BookingResponse {

	private Train train;
	private Booking booking;

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
