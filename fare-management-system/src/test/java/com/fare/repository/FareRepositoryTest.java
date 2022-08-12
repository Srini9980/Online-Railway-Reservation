package com.fare.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fare.pojo.Fare;

@SpringBootTest
public class FareRepositoryTest {
	
	@Mock
	private FareRepository fareRepository;

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
		assertEquals(2500, fare.getACChairClass());
	}
	
	@Test
	public void testFindAllFare() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		Fare fare1 = new Fare();
		fare1.setFareId(11);
		fare1.setACChairClass(3500);
		fare1.setFirstClass(2000);
		fare1.setSecondClass(650);
		fare1.setSleeperClass(900);
		fare1.setTatkal(300);
		
		Fare fare2 = new Fare();
		fare2.setFareId(12);
		fare2.setACChairClass(1500);
		fare2.setFirstClass(900);
		fare2.setSecondClass(450);
		fare2.setSleeperClass(600);
		fare2.setTatkal(150);
		
		List<Fare> fareList = new ArrayList<>();
		fareList.add(fare);
		fareList.add(fare1);
		fareList.add(fare2);
		
		when(fareRepository.findAll()).thenReturn(fareList);
		assertEquals(3, fareList.size());
	}
	
	@Test
	public void testFindFareById() {
		
		Fare fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(550);
		fare.setSleeperClass(800);
		fare.setTatkal(200);
		
		Optional<Fare> optionalFare = Optional.of(fare);
		when(fareRepository.findById(10)).thenReturn(optionalFare);
		assertEquals(2500, fare.getACChairClass());
	}
}
