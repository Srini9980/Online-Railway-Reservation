package com.passenger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.passenger.pojo.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	
    public Optional<Passenger> findByUserName(String userName);
    
    public Optional<Passenger> findByPhone(long phone);
    
    public List<Passenger> findByLocation(String location);
	
	@Query("select p from Passenger p where p.userName = :uname and p.password = :upassword")
	public Passenger login(@Param("uname")String userName, @Param("upassword")String password);
	

}
