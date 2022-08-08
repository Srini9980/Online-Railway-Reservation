package com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
	
	@GetMapping("/passenger/fallback")
	public String PassengerManagementServiceFallBack() {
		return "Passenger service is taking longer than the expected time."
				+ " Please try again later";
	}
	
	@GetMapping("/train/fallback")
	public String TrainManagementServiceFallBack() {
		return "Train service is taking longer than the expected time."
				+ " Please try again later";
	}
	
	@GetMapping("/ticket/fallback")
	public String TicketManagementServiceFallBack() {
		return "Booking service is taking longer than the expected time."
				+ " Please try again later";
	}
	
	@GetMapping("/pnr/fallback")
	public String PnrManagementServiceFallBack() {
		return "Pnr service is taking longer than the expected time."
				+ " Please try again later";
	}
	
	@GetMapping("/fare/fallback")
	public String FareManagementServiceFallBack() {
		return "Fare service is taking longer than the expected time."
				+ " Please try again later";
	}
	
	@GetMapping("/admin/fallback")
	public String AdminManagementServiceFallBack() {
		return "Admin service is taking longer than the expected time."
				+ " Please try again later";
	}

}
