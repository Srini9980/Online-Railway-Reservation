package com.passenger.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.passenger.pojo.Passenger;

@SpringBootTest
public class PassengerRepositoryTest {
	
	@Mock
	private PassengerRepository passengerRepository;

	@Test
	public void testSavePassenger() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("srinivas");
		passenger.setLastName("v");
		passenger.setAge(25);
		passenger.setGender("male");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("srinivas");
		passenger.setEmail("srinivas@mail.com");
		passenger.setPassword("12345");
		
		when(passengerRepository.save(passenger)).thenReturn(passenger);
		assertEquals("srinivas", passenger.getUserName());
	}
	
	@Test
	public void testFindAllPassenger() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("srinivas");
		passenger.setLastName("v");
		passenger.setAge(25);
		passenger.setGender("male");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("srinivas");
		passenger.setEmail("srinivas@mail.com");
		passenger.setPassword("12345");
		
		Passenger passenger1 = new Passenger();
		passenger1.setPassengerId(11);
		passenger1.setFirstName("praveen");
		passenger1.setLastName("j");
		passenger1.setAge(30);
		passenger1.setGender("male");
		passenger1.setPhone(454545);
		passenger1.setLocation("chennai");
		passenger1.setUserName("praveen");
		passenger1.setEmail("praveen@mail.com");
		passenger1.setPassword("454545");
		
		Passenger passenger2 = new Passenger();
		passenger2.setPassengerId(12);
		passenger2.setFirstName("pradeep");
		passenger2.setLastName("j");
		passenger2.setAge(27);
		passenger2.setGender("male");
		passenger2.setPhone(191919);
		passenger2.setLocation("hyderabad");
		passenger2.setUserName("pradeep");
		passenger2.setEmail("pradeep@mail.com");
		passenger2.setPassword("191919");
		
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		passengerList.add(passenger1);
		passengerList.add(passenger);
		
		when(passengerRepository.findAll()).thenReturn(passengerList);
		assertEquals(3, passengerList.size());
	}
	
	@Test
	public void testFindPassengerById() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("srinivas");
		passenger.setLastName("v");
		passenger.setAge(25);
		passenger.setGender("male");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("srinivas");
		passenger.setEmail("srinivas@mail.com");
		passenger.setPassword("12345");
		
		Optional<Passenger> optionalPassenger = Optional.of(passenger);
		when(passengerRepository.findById(10)).thenReturn(optionalPassenger);
		assertEquals("srinivas", passenger.getUserName());
	}
	
	@Test
	public void testLogin() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("srinivas");
		passenger.setLastName("v");
		passenger.setAge(25);
		passenger.setGender("male");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("srinivas");
		passenger.setEmail("srinivas@mail.com");
		passenger.setPassword("12345");
		
		when(passengerRepository.login("srinivas", "12345")).thenReturn(passenger);
		assertEquals("srinivas", passenger.getUserName());
	}
}
