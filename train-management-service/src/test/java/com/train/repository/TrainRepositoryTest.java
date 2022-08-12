package com.train.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.train.pojo.Train;

@SpringBootTest
public class TrainRepositoryTest {
	
	@Mock
	private TrainRepository trainRepository;
	
	@Test
	public void testSaveTrain() {
		
		Train train = new Train();
		train.setTrainId(10); 
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		when(trainRepository.save(train)).thenReturn(train);
		assertEquals("double-decker", train.getTrainName());
	}
	
	@Test
	public void testGetAllTrain() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		Train train1 = new Train();
		train1.setTrainId(11);
		train1.setTrainName("ysr-karwar express");
		train1.setSource("yeshwanthpur");
		train1.setDestination("karwar");
		train1.setDepartureTime("22:00");
		train1.setArrivalTime("7:00");
		train1.setSeatsAvailability(230);
		train1.setFareId(11);
		
		Train train2 = new Train();
		train2.setTrainId(12);
		train2.setTrainName("bullet train");
		train2.setSource("bangalore");
		train2.setDestination("mumbai");
		train2.setDepartureTime("15:00");
		train2.setArrivalTime("4:30");
		train2.setSeatsAvailability(180);
		train2.setFareId(12);
		
		List<Train> trainList = new ArrayList<>();
		trainList.add(train);
		trainList.add(train1);
		trainList.add(train2);
		
		when(trainRepository.findAll()).thenReturn(trainList);
		assertEquals(3, trainList.size());
	}
	
	@Test
	public void testGetTrainById() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		Optional<Train> optionalTrain = Optional.of(train);
		when(trainRepository.findById(10)).thenReturn(optionalTrain);
		assertEquals("double-decker", train.getTrainName());
	}
	
	@Test
	public void testGetTrainWithinRange() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		Train train1 = new Train();
		train1.setTrainId(11);
		train1.setTrainName("ysr-karwar express");
		train1.setSource("yeshwanthpur");
		train1.setDestination("karwar");
		train1.setDepartureTime("22:00");
		train1.setArrivalTime("7:00");
		train1.setSeatsAvailability(230);
		train1.setFareId(11);
		
		Train train2 = new Train();
		train2.setTrainId(12);
		train2.setTrainName("bullet train");
		train2.setSource("bangalore");
		train2.setDestination("mumbai");
		train2.setDepartureTime("15:00");
		train2.setArrivalTime("4:30");
		train2.setSeatsAvailability(180);
		train2.setFareId(12);
		
		List<Train> trainList = new ArrayList<>();
		trainList.add(train);
		trainList.add(train1);
		
		when(trainRepository.findAllTrainWithinRange("bangalore", "chennai")).thenReturn(trainList);
		assertEquals(2, trainList.size());
	}
	
	@Test
	public void testGetTrainByTrainName() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		when(trainRepository.findByTrainName("double-decker")).thenReturn(train);
		assertEquals("double-decker", train.getTrainName());
	}

}
