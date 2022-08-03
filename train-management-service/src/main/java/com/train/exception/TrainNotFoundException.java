package com.train.exception;

@SuppressWarnings("serial")
public class TrainNotFoundException extends RuntimeException {
	
	public TrainNotFoundException(String message) {
		super(message);
	}

}
