package com.ticket.pojo;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket_booking")
public class Booking {

	@Transient
	public static final String SEQUENCE_NAME = "booking_sequence";

	@Id
	private int bookingId;
	@NotNull(message = "Please enter your name")
	private String passengerName;
	@NotNull(message = "Please enter your age")
	private int age;
	@NotNull(message = "Gender is reuired")
	private String gender;
	@NotNull(message = "Date of journey is required")
	@FutureOrPresent(message = "Date should not be in past")
	private LocalDate dateOfJourney;
	@NotNull(message = "Please enter the seat type")
	private String seatType;
	@NotNull(message = "Train id is required")
	private int trainId;

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

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

}
