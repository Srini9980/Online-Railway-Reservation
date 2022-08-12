package com.train.controller;

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
import com.train.dto.Fare;
import com.train.dto.ResponseTemplate;
import com.train.pojo.Train;
import com.train.service.TrainService;

@ExtendWith(MockitoExtension.class)
public class TrainControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private TrainController trainController;
	
	@Mock
	private TrainService trainService;
	private Train train;
	private ResponseTemplate responseTemplate;
	private List<Train> trains;
	
	@BeforeEach
	public void setUp() {
		
		train = new Train();
		train.setTrainId(10);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setArrivalTime("8:00");
		train.setDepartureTime("15:00");
		train.setSeatsAvailability(200);
		train.setFareId(11);
		
		mockMvc = MockMvcBuilders.standaloneSetup(trainController).build();
	}
	
	@Test
	public void testAddTrain() throws Exception {
		
		when(trainService.saveTrain(any())).thenReturn(train);
		mockMvc.perform(
				post("/train/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(train)))
			    .andExpect(status().isOk());
	            verify(trainService,times(1)).saveTrain(any());
	}
	
	@Test
	public void testGetAllTrain() throws Exception {
		
		when(trainService.getAllTrain()).thenReturn(trains);
		mockMvc.perform(get("/train/all").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(trains))).andDo(print());
		verify(trainService, times(1)).getAllTrain();
	}
	
	@Test
	public void testGetTrainByFare() throws Exception {
		
		Fare fare = new Fare();
		fare.setFareId(11);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(800);
		fare.setSleeperClass(550);
		fare.setTatkal(230);
		responseTemplate = new ResponseTemplate();
		responseTemplate.setFare(fare);
		responseTemplate.setTrain(train);
		
		when(trainService.getTrainByFare(train.getTrainId())).thenReturn(responseTemplate);
		mockMvc.perform(get("/train/fare/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(responseTemplate))).andDo(print());
		verify(trainService,times(1)).getTrainByFare(10);
	}
	
	@Test
	public void testGetTrainById() throws Exception {
		
		when(trainService.getTrainById(10)).thenReturn(train);
		mockMvc.perform(get("/train/find/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(train))).andDo(print());
		verify(trainService,times(1)).getTrainById(10);
	}
	
	@Test
	public void testUpdateTrain() throws Exception {
		
		when(trainService.updateTrain(any())).thenReturn(train);
		mockMvc.perform(put("/train/update").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(train))).andExpect(status().isOk());
		verify(trainService,times(1)).updateTrain(any());
	}
	
	@Test
	public void testDeleteTrainById() throws Exception {
		
		mockMvc.perform(delete("/train/delete/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(train))).andExpect(status().isOk());
		verify(trainService,times(1)).deleteTrainById(10);
		
	}
	
	@Test
	public void testGetTrainWithinRoute() throws Exception {
		
		when(trainService.getAllTrainWithinRange("bangalore", "chennai")).thenReturn(trains);
		mockMvc.perform(get("/train/byroute/bangalore/chennai").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(trains))).andDo(print());
		verify(trainService,times(1)).getAllTrainWithinRange("bangalore", "chennai");
	}
	
	@Test
	public void testGetTrainByName() throws Exception {
		
		when(trainService.getAllTrainByTrainName("double-decker")).thenReturn(train);
		mockMvc.perform(get("/train/byname/double-decker").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(train))).andDo(print());
		verify(trainService,times(1)).getAllTrainByTrainName("double-decker");
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
