package com.pnr.dto;

import com.pnr.pojo.Pnr;

public class PnrResponse {

	private Pnr pnr;
	private Booking booking;
	private Train train;

	public Pnr getPnr() {
		return pnr;
	}

	public void setPnr(Pnr pnr) {
		this.pnr = pnr;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

}
