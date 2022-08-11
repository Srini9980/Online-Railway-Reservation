package com.fare.exception;

@SuppressWarnings("serial")
public class FareIdAlreadyExistsException extends RuntimeException {
	
	public FareIdAlreadyExistsException(String message) {
		super(message);
	}

}
