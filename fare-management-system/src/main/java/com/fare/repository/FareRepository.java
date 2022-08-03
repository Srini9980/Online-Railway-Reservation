package com.fare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fare.pojo.Fare;

public interface FareRepository extends MongoRepository<Fare, Integer> {

}
