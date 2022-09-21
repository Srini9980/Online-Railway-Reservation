package com.pnr.pojo;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pnr_collection")
public class Pnr {

//	@Transient
//	public static final String SEQUENCE_NAME = "pnr_sequence";

	@Id
	private int pnrId;
	@NotNull(message = "PNR status is required")
	private String pnrStatus;
	@NotNull(message = "Booking is required")
	private int bookingId;
	@NotNull(message = "Train id is required")
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

//	public static String getSequenceName() {
//		return SEQUENCE_NAME;
//	}

}
