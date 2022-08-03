package com.train.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.train.pojo.Train;

public interface TrainRepository extends MongoRepository<Train, Integer> {
	
	public Train findByTrainName(String trainName);

}
