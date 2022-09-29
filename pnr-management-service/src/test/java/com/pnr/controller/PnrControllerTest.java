package com.pnr.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.pnr.dto.Booking;
import com.pnr.dto.PnrResponse;
import com.pnr.dto.Train;
import com.pnr.pojo.Pnr;
import com.pnr.service.PnrService;

@ExtendWith(MockitoExtension.class)
public class PnrControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private PnrController pnrController;

	@Mock
	private PnrService pnrService;
	private Pnr pnr;
	private List<Pnr> allPnr;
	private Booking booking;
	private Train train;
	private PnrResponse pnrResponse;

	@BeforeEach
	public void setUp() {

		pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);

		mockMvc = MockMvcBuilders.standaloneSetup(pnrController).build();
	}

	@Test
	public void testSavePnr() throws Exception {

		when(pnrService.savePnr(any())).thenReturn(pnr);
		mockMvc.perform(post("/pnr/save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pnr)))
				.andExpect(status().isOk());
		verify(pnrService, times(1)).savePnr(any());
	}

	@Test
	public void testGetPnrByBookingId() throws Exception {

		booking = new Booking();
		booking.setBookingId(11);
		booking.setSeatType("aCChaiClass");

		train = new Train();
		train.setTrainId(12);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("8:00");
		train.setArrivalTime("15:30");
		pnrResponse = new PnrResponse();
		pnrResponse.setTrain(train);
		pnrResponse.setBooking(booking);
		pnrResponse.setPnr(pnr);

		when(pnrService.getPnrByBookingId(10)).thenReturn(pnrResponse);
		mockMvc.perform(get("/pnr/byid/10").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pnrResponse)))
				.andDo(print());
		verify(pnrService, times(1)).getPnrByBookingId(10);

	}

	@Test
	public void testGetPnrById() throws Exception {

		when(pnrService.getPnrById(10)).thenReturn(pnr);
		mockMvc.perform(get("/pnr/pnrById/10").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pnr)))
				.andDo(print());
		verify(pnrService, times(1)).getPnrById(10);

	}
	
	@Test
	public void testGetAllPnr() throws Exception {
		
		when(pnrService.getAllPnr()).thenReturn(allPnr);
		mockMvc.perform(get("/pnr/all").contentType(MediaType.APPLICATION_JSON).content(asJsonString(allPnr)))
		.andDo(print());
		verify(pnrService, times(1)).getAllPnr();
		
	}
	
	@Test
	public void testDeletePnrById() throws Exception {
		
		mockMvc.perform(delete("/pnr/delete/10").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pnr)))
		.andExpect(status().isOk());
		verify(pnrService, times(1)).deletePnr(10);
		
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
