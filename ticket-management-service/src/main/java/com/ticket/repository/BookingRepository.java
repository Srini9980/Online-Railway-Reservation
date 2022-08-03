package com.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ticket.pojo.Booking;

public interface BookingRepository extends MongoRepository<Booking, Integer> {

}
