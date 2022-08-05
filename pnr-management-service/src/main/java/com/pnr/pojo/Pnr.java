package com.pnr.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pnr_collection")
public class Pnr {

	@Id
	private int pnrId;
	private String pnrStatus;
	private int bookingId;
	private int trainId;

	public int getPnrId() {
		return pnrId;
	}

	public void setPnrId(int pnrId) {
		this.pnrId = pnrId;
	}

	public String getPnrStatus() {
		return pnrStatus;
	}

	public void setPnrStatus(String pnrStatus) {
		this.pnrStatus = pnrStatus;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

}
