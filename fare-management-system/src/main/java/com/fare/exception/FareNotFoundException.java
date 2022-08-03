package com.fare.exception;

@SuppressWarnings("serial")
public class FareNotFoundException extends RuntimeException {
	
	public FareNotFoundException(String msg) {
		super(msg);
	}	
	
}