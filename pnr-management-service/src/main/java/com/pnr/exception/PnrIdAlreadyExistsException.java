package com.pnr.exception;

@SuppressWarnings("serial")
public class PnrIdAlreadyExistsException extends RuntimeException {
	
	public PnrIdAlreadyExistsException(String message) {
		super(message);
	}

}
