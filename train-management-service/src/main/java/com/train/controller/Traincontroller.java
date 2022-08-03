package com.train.controller;

import java.util.List;

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
public class Traincontroller {
	
	@Autowired
	private TrainService trainService;
	
	@PostMapping("/train/add")
	public ResponseEntity<Train> addAdmin(@RequestBody Train train) {

		Train newTrain = trainService.saveTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(newTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/train/all")
	public List<Train> fetchAllTrain() {

		List<Train> allTrain = trainService.getAllTrain();
		return allTrain;
	}
	
	@GetMapping("/train/fare/{trainId}")
	public ResponseTemplate fetchById(@PathVariable("trainId") int trainId) {

		ResponseTemplate trainById = trainService.getTrainByFare(trainId);
		return trainById;
	}
	
	 @GetMapping("/train/find/{trainId}")
		public ResponseEntity<Object> fetchTrainById(@PathVariable("trainId") int trainId) {
			
			ResponseEntity<Object> responseEntity = null;		
			Train trainById = trainService.getTrainById(trainId);
			responseEntity = new ResponseEntity<>(trainById,HttpStatus.OK);				
			return responseEntity;
		}
	
	@PutMapping("/train/update")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train) {

		Train updatedTrain = trainService.updateTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(updatedTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/train/delete/{trainId}")
	public ResponseEntity<String> deletePassengerById(@PathVariable("trainId") int trainId) {

		trainService.deleteTrainById(trainId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/train/byname/{trainName}")
	public ResponseEntity<Train> fetchTrainByName(@PathVariable("trainName") String trainName) {
		
		Train trainByName = trainService.getAllTrainByTrainName(trainName);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(trainByName, HttpStatus.OK);
		return responseEntity;
		
	}
}
