package com.fare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fare.exception.FareNotFoundException;
import com.fare.pojo.Fare;
import com.fare.repository.FareRepository;

@SpringBootTest
public class FareServiceTest {
	
	@InjectMocks
	private FareService fareService = new FareServiceImpl();
	
	@Mock
	private FareRepository fareRepository;
	
	@Test
	public void testGetFareById() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		Optional<Fare> optionalFare = Optional.of(fare);
		when(fareRepository.findById(10)).thenReturn(optionalFare);
		Fare myFare = fareService.getFareById(10);
		assertEquals(2500, myFare.getACChairClass());
	}
	
	@Test
	public void testGetFareByIdWithException() {
		
		when(fareRepository.findById(10)).thenThrow(FareNotFoundException.class);
		assertThrows(FareNotFoundException.class, () -> fareService.getFareById(10));
	}
	
	@Test
	public void testSaveFare() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		when(fareRepository.save(fare)).thenReturn(fare);
		Fare newFare = fareService.saveFare(fare);
		assertEquals(2500, newFare.getACChairClass());
		verify(fareRepository,times(1)).save(fare);
	}
	
	@Test
	public void testUpdateFare() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		Optional<Fare> optionalFare = Optional.of(fare);
		when(fareRepository.findById(10)).thenReturn(optionalFare);
		fareService.modifyFare(fare);
		verify(fareRepository,times(1)).findById(10);
		verify(fareRepository,times(1)).save(fare);
		
	}
	
	@Test
	public void testUpdateFareWithException() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		when(fareRepository.findById(11)).thenThrow(FareNotFoundException.class);
		assertThrows(FareNotFoundException.class, () -> fareService.modifyFare(fare));
	}
	
	@Test
	public void testDeleteFareById() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		Optional<Fare> optionalFare = Optional.of(fare);
		when(fareRepository.findById(10)).thenReturn(optionalFare);
		fareService.deleteFare(10);
		verify(fareRepository,times(1)).findById(10);
		verify(fareRepository,times(1)).deleteById(10);
	}
	
	@Test
	public void testDeleteFareByIdWithException() {
		
		when(fareRepository.findById(11)).thenThrow(FareNotFoundException.class);
		assertThrows(FareNotFoundException.class, () -> fareService.deleteFare(11));
	}
}
