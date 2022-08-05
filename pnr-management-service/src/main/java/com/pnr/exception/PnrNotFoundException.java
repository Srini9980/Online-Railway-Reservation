package com.pnr.exception;

@SuppressWarnings("serial")
public class PnrNotFoundException extends RuntimeException {

	public PnrNotFoundException(String msg) {
		super(msg);
	}

}