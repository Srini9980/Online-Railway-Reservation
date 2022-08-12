package com.passenger.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.passenger.pojo.Passenger;
import com.passenger.service.PassengerService;

@ExtendWith(MockitoExtension.class)
public class PassengerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private PassengerController passengerController;

	@Mock
	private PassengerService passengerService;
	private Optional<Passenger> optionalPassenger;
	private Passenger passenger;
	private List<Passenger> passengers;
	
	@BeforeEach
	public void setup() {
		
		passenger = new Passenger();
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
		
		mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
		
	}
	
	@Test
	public void testAddPassenger() throws Exception {
		
		when(passengerService.savePassenger(any())).thenReturn(passenger);
		mockMvc.perform(post("/passenger/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andExpect(status().isOk());
		verify(passengerService,times(1)).savePassenger(any());
	}
	
	@Test
	public void testGetAllPassenger() throws Exception {
		
		when(passengerService.getAllPassenger()).thenReturn(passengers);
		mockMvc.perform(get("/passenger/all").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andDo(print());
		verify(passengerService,times(1)).getAllPassenger();
	}
	
	@Test
	public void testGetPassengerById() throws Exception {
		
		passenger = new Passenger();
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
		
		when(passengerService.getPassengerById(10)).thenReturn(passenger);
		mockMvc.perform(get("/passenger/find/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andDo(print());
		verify(passengerService,times(1)).getPassengerById(10);
	}
	
	@Test
	public void testUpdatePssenger() throws Exception {
		
		passenger = new Passenger();
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
		
		when(passengerService.updatePassenger(any())).thenReturn(passenger);
		mockMvc.perform(put("/passenger/update").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andExpect(status().isOk());
		verify(passengerService,times(1)).updatePassenger(any());
	}
	
	@Test
	public void testDeletePassengerById() throws Exception {
		
		passenger = new Passenger();
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
		
//		when(passengerService.getPassengerById(10)).thenReturn(passenger);
		mockMvc.perform(delete("/passenger/delete/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andExpect(status().isOk());
		verify(passengerService,times(1)).deletePassengerById(10);
	}
	
	@Test
	public void testGetPassengerByUserName() throws Exception {
		
		when(passengerService.getAllPassengerByUserName(passenger.getUserName())).thenReturn(optionalPassenger);
		mockMvc.perform(get("/passenger/byusername/srinivas").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(optionalPassenger))).andDo(print());
		verify(passengerService,times(1)).getAllPassengerByUserName(passenger.getUserName());
	}
	
	@Test
	public void testPassengerByPhone() throws Exception {
		
		when(passengerService.getAllPassengerByPhone(998078)).thenReturn(optionalPassenger);
		mockMvc.perform(get("/passenger/byphone/998078").contentType(MediaType.APPLICATION_JSON)
		       .content(asJsonString(optionalPassenger))).andDo(print());
		verify(passengerService,times(1)).getAllPassengerByPhone(998078);
	}
	
	@Test
	public void testPassengerByLocation()throws Exception {
		
		when(passengerService.getAllPassengerByLocation(passenger.getLocation())).thenReturn(passengers);
		mockMvc.perform(get("/passenger/bylocation/bangalore").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passengers))).andDo(print());
		verify(passengerService,times(1)).getAllPassengerByLocation(passenger.getLocation());
	}
	
	@Test
	public void testDoLogin() throws Exception {
		
		when(passengerService.doLogin("srinivas", "12345")).thenReturn(passenger);
		mockMvc.perform(post("/passenger/login").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(passenger))).andDo(print());
		verify(passengerService,times(1)).doLogin("srinivas", "12345");
	}
	
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper(); 
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception); 
		}
	}
}
