package com.ticket.exception;

@SuppressWarnings("serial")
public class BookingNotFoundException extends RuntimeException {
	public BookingNotFoundException(String msg) {
		super(msg);
	}

}