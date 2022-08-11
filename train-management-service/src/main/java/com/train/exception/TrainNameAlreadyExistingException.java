package com.train.exception;

@SuppressWarnings("serial")
public class TrainNameAlreadyExistingException extends RuntimeException {
	
	public TrainNameAlreadyExistingException(String message) {
		super(message);
	}

}
