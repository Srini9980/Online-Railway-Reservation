package com.pnr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pnr.pojo.Pnr;

@Repository
public interface PnrRepository extends MongoRepository<Pnr, Integer> {

}
