package com.train.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.train.dto.Fare;
import com.train.dto.ResponseTemplate;
import com.train.exception.TrainNameAlreadyExistingException;
import com.train.exception.TrainNotFoundException;
import com.train.pojo.Train;
import com.train.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Train saveTrain(Train train) {
		
//		Train trainByName = trainRepository.findByTrainName(train.getTrainName());
//		if(trainByName.getTrainName() == train.getTrainName()) {
//			throw new TrainNameAlreadyExistingException("Train name already exists");
//		}
		Train newTrain = trainRepository.save(train); 
		return newTrain;
	}

	@Override
	public List<Train> getAllTrain() {

		List<Train> allTrain = trainRepository.findAll();
		return allTrain;
	}

	@Override
	public Train getTrainById(int trainId) {
		
		Optional<Train> optionalTrain = trainRepository.findById(trainId);
		if (optionalTrain.isEmpty()) {
			throw new TrainNotFoundException("No train found with this id :" + trainId);
		}
		Train trainById = optionalTrain.get();
		return trainById;
	}
	
	@Override
	public ResponseTemplate getTrainByFare(int trainId) {
		
		ResponseTemplate response= new ResponseTemplate();
		Optional<Train> optionalTrain=trainRepository.findById(trainId);
		if(optionalTrain.isEmpty()) {
			throw new TrainNotFoundException("No train found with this id :"+ trainId);
		}
		Train train=optionalTrain.get();
		Fare fare=restTemplate.getForObject("http://FARE-MANAGEMENT-SERVICE/fare/find/" + train.getFareId(), Fare.class);
		response.setFare(fare);
		response.setTrain(train);
		return response;
	}

	@Override
	public Train updateTrain(Train train) {
		
		Optional<Train> optionalTrain = trainRepository.findById(train.getTrainId());
		if (optionalTrain.isEmpty()) {
			throw new TrainNotFoundException("No train found with this id :" + train.getTrainId());
		}
		
		Train updatedTrain = trainRepository.save(train);
		return updatedTrain;
	}

	@Override
	public void deleteTrainById(int trainId) {
		
		Optional<Train> optionalTrain = trainRepository.findById(trainId);
		if (optionalTrain.isEmpty()) {
			throw new TrainNotFoundException("No train found with this id :" + trainId);
		}
		
		trainRepository.deleteById(trainId);
		
	}

	@Override
	public Train getAllTrainByTrainName(String trainName) {
		
		Train trainByName = trainRepository.findByTrainName(trainName);
		if(trainByName == null) {
			throw new TrainNotFoundException("No train found with this name :" + trainName);
		}
		
		return trainByName;
	}

	@Override
	public List<Train> getAllTrainWithinRange(String source, String destination) {
		
		List<Train> trains = trainRepository.findAllTrainWithinRange(source, destination);
		if(trains.isEmpty()) {
			throw new TrainNotFoundException("No trains found with this route");
		}
		return trains;
	}

}
