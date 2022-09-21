package com.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FallBackMethodController {
	
	@GetMapping("/login/fallback")
	public String LoginManagementServiceFallBack() {
		return "Login service is taking longer than the expected time."
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
	
	@GetMapping("/user/fallback")
	public String UserManagementServiceFallBack() {
		return "User service is taking longer than the expected time."
				+ " Please try again later";
	}

}
