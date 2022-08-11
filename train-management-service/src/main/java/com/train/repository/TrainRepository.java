package com.train.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.train.pojo.Train;

public interface TrainRepository extends MongoRepository<Train, Integer> {
	
	public Train findByTrainName(String trainName);
	
	@Query("{'source':?0, 'destination':?1 }")
	public List<Train> findAllTrainWithinRange(String source, String destination);

}
