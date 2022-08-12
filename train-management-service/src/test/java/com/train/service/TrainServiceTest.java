package com.train.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.train.dto.Fare;
import com.train.dto.ResponseTemplate;
import com.train.exception.TrainNotFoundException;
import com.train.pojo.Train;
import com.train.repository.TrainRepository;

@SpringBootTest
public class TrainServiceTest {
	
	@InjectMocks
	private TrainService trainService = new TrainServiceImpl();
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private TrainRepository trainRepository;
	
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
		Train myTrain = trainService.getTrainById(10);
		assertEquals("double-decker", myTrain.getTrainName());
	}
	
	@Test
	public void testGetTrainByIdWithException() {
		
		when(trainRepository.findById(10)).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> trainService.getTrainById(10));
	}
	
	@Test
	public void testGetAllTrains() {
		
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
		List<Train> trains = trainService.getAllTrain();
		assertEquals(3, trains.size());
	}
	
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
		Train newTrain = trainService.saveTrain(train);
		assertEquals("bangalore",newTrain.getSource());
		verify(trainRepository,times(1)).save(train);
	}
	
	@Test
	public void testUpdateTrain() {
		
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
		trainService.updateTrain(train);
		verify(trainRepository,times(1)).findById(10);
		verify(trainRepository,times(1)).save(train);
		
	}
	
	@Test
	public void testUpdateTrainWithException() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(10);
		
		when(trainRepository.findById(10)).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> trainService.updateTrain(train));
	}
	
	@Test
	public void testDeleteTrainById() {
		
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
		trainService.deleteTrainById(10);
		verify(trainRepository,times(1)).findById(10);
		verify(trainRepository,times(1)).deleteById(10);
	}
	
	@Test
	public void testDeleteTrainByIdWithexception() {
		
		when(trainRepository.findById(10)).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> trainService.deleteTrainById(10));
	}
	
	@Test
	public void testGetAllTrainWithinRange() {
		
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
		
		when(trainRepository.findAllTrainWithinRange(train.getSource(), train.getDestination())).thenReturn(trainList);
		List<Train> trainByRoute = trainService.getAllTrainWithinRange(train.getSource(), train.getDestination());
		assertEquals(3, trainByRoute.size());
		
	}
	
	@Test
	public void testGetAllTrainWithinRangeWithException() {
		
		when(trainRepository.findAllTrainWithinRange("bangalore", "chennai")).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> trainService.getAllTrainWithinRange("bangalore", "mumbai"));
		
	}
	
	@Test
	public void testGetAllTrainByTrainName() {
		
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
		trainService.getAllTrainByTrainName("double-decker");
		assertEquals("double-decker", train.getTrainName());
		
	}
	
	@Test
	public void testGetAllTrainByTrainNameWithException() {
		
		when(trainRepository.findByTrainName("double-decker")).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> trainService.getAllTrainByTrainName("double-decker"));
		
	}
	
	@Test
	public void testGetTrainByFare() {
		
		Train train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("10:00");
		train.setArrivalTime("15:30");
		train.setSeatsAvailability(200);
		train.setFareId(11);
		
		Fare fare = new Fare();
		fare.setFareId(11);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(800);
		fare.setSleeperClass(550);
		fare.setTatkal(200);
		
		Optional<Train> optionalTrain = Optional.of(train);
		when(trainRepository.findById(10)).thenReturn(optionalTrain);
		optionalTrain.get();
		ResponseTemplate responseTemplate = trainService.getTrainByFare(train.getTrainId());
		responseTemplate.setFare(fare);
		responseTemplate.setTrain(train);
		assertEquals(fare, responseTemplate.getFare());
	}
	

}
