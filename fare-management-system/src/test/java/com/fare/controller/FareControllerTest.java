package com.fare.controller;

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

import com.fare.pojo.Fare;
import com.fare.service.FareService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class FareControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private FareController fareController;
	
	@Mock
	private FareService fareService;
	private Fare fare;
	
	@BeforeEach
	public void setUp() {
		
		fare = new Fare();
		fare.setFareId(10);
		fare.setACChairClass(2500);
		fare.setFirstClass(1000);
		fare.setSecondClass(800);
		fare.setSleeperClass(550);
		fare.setTatkal(230);
		
		mockMvc = MockMvcBuilders.standaloneSetup(fareController).build();
	}
	
	@Test
	public void testAddFare() throws Exception {
		
		when(fareService.saveFare(any())).thenReturn(fare);
		mockMvc.perform(post("/fare/save").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fare))).andExpect(status().isOk());
		verify(fareService,times(1)).saveFare(any());
	}
	
	@Test
	public void testGetFareById() throws Exception {
		
		when(fareService.getFareById(10)).thenReturn(fare);
		mockMvc.perform(get("/fare/find/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fare))).andDo(print());
		verify(fareService,times(1)).getFareById(10);
	}
	
	@Test
	public void testModifyFare() throws Exception {
		
		when(fareService.modifyFare(any())).thenReturn(fare);
		mockMvc.perform(put("/fare/modify").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fare))).andExpect(status().isOk());
		verify(fareService,times(1)).modifyFare(any());
	}
	
	@Test
	public void testDeleteFareByid() throws Exception {
		
		mockMvc.perform(delete("/fare/delete/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fare))).andExpect(status().isOk());
		verify(fareService,times(1)).deleteFare(10);
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
