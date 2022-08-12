package com.ticket.controller;

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
import com.ticket.dto.BookingResponse;
import com.ticket.dto.Train;
import com.ticket.pojo.Booking;
import com.ticket.service.BookingService;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private BookingController bookingController;
	
	@Mock
	private BookingService bookingService;
	private Train train;
	private Booking booking;
	private List<Booking> bookings;
	private BookingResponse bookingResponse;

	@BeforeEach
	public void setUpt() {
		
		booking = new Booking();
		booking.setBookingId(10);
		booking.setPassengerName("srinivas");
		booking.setAge(25);
		booking.setGender("male");
//		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);
		
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}
	
	@Test
	public void testSaveBooking() throws Exception {
		
		train = new Train();
		train.setTrainId(11);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		bookingResponse = new BookingResponse();
		bookingResponse.setBooking(booking);
		bookingResponse.setTrain(train);
		
		when(bookingService.saveBooking(any())).thenReturn(bookingResponse);
		mockMvc.perform(post("/booking/save").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(booking))).andExpect(status().isOk());
		verify(bookingService,times(1)).saveBooking(any());
	}
	
	@Test
	public void testDeleteBookingById()throws Exception {
		
		mockMvc.perform(delete("/booking/delete/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(booking))).andExpect(status().isOk());
		verify(bookingService,times(1)).deleteBooking(10);
	}
	
	@Test
	public void testGetAllBooking() throws Exception {
		
		when(bookingService.getAllBooking()).thenReturn(bookings);
		mockMvc.perform(get("/booking/all").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookings))).andDo(print());
		verify(bookingService,times(1)).getAllBooking();
	}
	
	@Test
	public void testGetBookingById() throws Exception {
		
		when(bookingService.getBookingById(10)).thenReturn(booking);
		mockMvc.perform(get("/booking/find/10").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(booking))).andDo(print());
		verify(bookingService,times(1)).getBookingById(10);
		
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
