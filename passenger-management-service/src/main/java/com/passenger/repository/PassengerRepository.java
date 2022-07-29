package com.passenger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.passenger.pojo.Passenger;

public interface PassengerRepository  extends MongoRepository<Passenger, Integer>{
	
public Optional<Passenger> findByUserName(String userName);
    
    @Query("{'phone':?0 }")
    public Optional<Passenger> findByPhone(long phone);
    
    @Query("{'location':?0 }")
    public List<Passenger> findByLocation(String location);
	
    @Query("{'userName':?0, 'password':?1 }")
	public Passenger login(String userName, String password);

}
