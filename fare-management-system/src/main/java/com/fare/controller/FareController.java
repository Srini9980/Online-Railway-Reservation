package com.fare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fare.pojo.Fare;
import com.fare.service.FareService;

@RestController
public class FareController {
	
	Logger logger = LoggerFactory.getLogger(FareController.class);
	
	@Autowired
	private FareService fareService;
	
	@PostMapping("/fare/save")
	public ResponseEntity<Fare> addFare(@RequestBody Fare fare) {
		
		logger.info("adaFare method from Fare controller is accessed");
		
		Fare newFare = fareService.saveFare(fare);
		ResponseEntity<Fare> responseEntity = new ResponseEntity<>(newFare, HttpStatus.OK);
		return responseEntity;

	}
	
	@GetMapping("/fare/find/{fareId}")
	public ResponseEntity<Object> fetchFareById(@PathVariable("fareId") int fareId) {
		
		logger.info("FareById method from Fare controller is accessed");
		
		ResponseEntity<Object> responseEntity = null;
		Fare fare = fareService.getFare(fareId);
		responseEntity = new ResponseEntity<Object>(fare, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/fare/modify")
	public ResponseEntity<Fare> modifyFare(@RequestBody Fare fare) {
		
		logger.info("updateFare method from Fare controller is accessed");
		
		Fare modifiedFare=fareService.modifyFare(fare);
		ResponseEntity<Fare> responseEntity = new ResponseEntity<>(modifiedFare, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/fare/delete/{fId}")
	public ResponseEntity<String> removeFare(@PathVariable("fId") int fareId) {
		
		logger.info("deleteFare method from Fare controller is accessed");

		fareService.deleteFare(fareId);
		return new ResponseEntity<>("Fare removed Successfully.", HttpStatus.OK);
	}

}
