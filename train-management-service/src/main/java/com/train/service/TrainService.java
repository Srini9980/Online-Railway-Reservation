package com.train.service;

import java.util.List;

import com.train.dto.ResponseTemplate;
import com.train.pojo.Train;

public interface TrainService {
	
	public Train saveTrain(Train train);

	public List<Train> getAllTrain();

	public Train getTrainById(int trainId);
	
	public ResponseTemplate getTrainByFare(int trainId);

	public Train updateTrain(Train train);

	public void deleteTrainById(int trainId);
	
	public Train getAllTrainByTrainName(String trainName);
	
	public List<Train> getAllTrainWithinRange(String source, String destination);

}
