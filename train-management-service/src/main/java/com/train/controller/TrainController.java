package com.train.controller;

import java.util.List;

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

import com.train.dto.ResponseTemplate;
import com.train.pojo.Train;
import com.train.service.TrainService;

@RestController
public class TrainController {
	
	Logger logger = LoggerFactory.getLogger(TrainController.class);
	
	@Autowired
	private TrainService trainService;
	
	@PostMapping("/train/add")
	public ResponseEntity<Train> addTrain(@RequestBody Train train) {
		
		logger.info("addTrain method from Train controller is accessed");

		Train newTrain = trainService.saveTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(newTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/train/all")
	public List<Train> fetchAllTrain() {
		
		logger.info("fetchAllTrain method from Train controller is accessed");

		List<Train> allTrain = trainService.getAllTrain();
		return allTrain;
	}
	
	@GetMapping("/train/fare/{trainId}")
	public ResponseTemplate fetchById(@PathVariable("trainId") int trainId) {
		
		logger.info("TrainByFare method from Train controller is accessed");

		ResponseTemplate trainById = trainService.getTrainByFare(trainId);
		return trainById;
	}
	
	 @GetMapping("/train/find/{trainId}")
		public ResponseEntity<Object> fetchTrainById(@PathVariable("trainId") int trainId) {
		 
		 logger.info("TrainById method from Train controller is accessed");
			
			ResponseEntity<Object> responseEntity = null;		
			Train trainById = trainService.getTrainById(trainId);
			responseEntity = new ResponseEntity<>(trainById,HttpStatus.OK);				
			return responseEntity;
		}
	
	@PutMapping("/train/update")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train) {
		
		logger.info("updateTrain method from Train controller is accessed");

		Train updatedTrain = trainService.updateTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(updatedTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/train/delete/{trainId}")
	public ResponseEntity<String> deletePassengerById(@PathVariable("trainId") int trainId) {
		
		logger.info("deleteTrain method from Train controller is accessed");

		trainService.deleteTrainById(trainId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/train/byname/{trainName}")
	public ResponseEntity<Train> fetchTrainByName(@PathVariable("trainName") String trainName) {
		
		logger.info("TrainByName method from Train controller is accessed");
		
		Train trainByName = trainService.getAllTrainByTrainName(trainName);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(trainByName, HttpStatus.OK);
		return responseEntity;
		
	}
}
