package com.pnr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.pnr.dto.Booking;
import com.pnr.dto.PnrResponse;
import com.pnr.dto.Train;
import com.pnr.exception.PnrNotFoundException;
import com.pnr.pojo.Pnr;
import com.pnr.repository.PnrRepository;

@SpringBootTest
public class PnrServiceTest {
	
	@InjectMocks
	private PnrService pnrService = new PnrServiceImpl();
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private PnrRepository pnrRepository;
	
	@Test
	public void testSavePnr() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		when(pnrRepository.save(pnr)).thenReturn(pnr);
		Pnr newPnr = pnrService.savePnr(pnr);
		assertEquals("confirmed", newPnr.getPnrStatus());
		verify(pnrRepository,times(1)).save(pnr);
	}
	
	@Test
	public void testGetPnrByBookingId() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		Booking booking = new Booking();
		booking.setBookingId(11);
		booking.setDateOfJourney(LocalDate.of(2022, 12, 11));
		booking.setSeatType("aCChairClass");
		
		Train train = new Train();
		train.setTrainId(12);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("8:00");
		train.setArrivalTime("15:30");
		
		Optional<Pnr> optionalPnr = Optional.of(pnr);
		when(pnrRepository.findById(10)).thenReturn(optionalPnr);
		optionalPnr.get();
		PnrResponse pnrResponse = pnrService.getPnrByBookingId(10);
		pnrResponse.setBooking(booking);
		pnrResponse.setTrain(train);
		pnrResponse.setPnr(pnr);
		assertEquals(booking, pnrResponse.getBooking());
	}
	
	@Test
	public void testGetPnrByBookingIdWithException() {
		
		when(pnrRepository.findById(11)).thenThrow(PnrNotFoundException.class);
		assertThrows(PnrNotFoundException.class, () -> pnrService.getPnrByBookingId(11));
	}
	
	@Test
	public void testGetAllPnr() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		Pnr pnr1 = new Pnr();
		pnr1.setPnrId(11);
		pnr1.setPnrStatus("confirmed");
		pnr1.setBookingId(12);
		pnr1.setTrainId(13);
		
		Pnr pnr2 = new Pnr();
		pnr2.setPnrId(12);
		pnr2.setPnrStatus("confirmed");
		pnr2.setBookingId(13);
		pnr2.setTrainId(11);
		
		List<Pnr> pnrList = new ArrayList<>();
		pnrList.add(pnr);
		pnrList.add(pnr1);
		pnrList.add(pnr2);
		
		when(pnrRepository.findAll()).thenReturn(pnrList);
		List<Pnr> allPnr = pnrService.getAllPnr();
		assertEquals(3, allPnr.size());
		
	}
	
	@Test
	public void testDeletePnr() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		Optional<Pnr> optionalPnr = Optional.of(pnr);
		when(pnrRepository.findById(10)).thenReturn(optionalPnr);
		pnrService.deletePnr(10);
		verify(pnrRepository, times(1)).findById(10);
		verify(pnrRepository, times(1)).deleteById(10);;
		
	}
	
	@Test
	public void testDeletePnrWithException() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		when(pnrRepository.findById(11)).thenThrow(PnrNotFoundException.class);
		assertThrows(PnrNotFoundException.class, () -> pnrService.deletePnr(10));
		
	}
	
	@Test 
	public void testGetPnrById() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		Optional<Pnr> optionalPnr = Optional.of(pnr);
		when(pnrRepository.findById(10)).thenReturn(optionalPnr);
		Pnr myPnr = pnrService.getPnrById(10);
		assertEquals("confirmed", myPnr.getPnrStatus());
		
	}
	
	@Test
	public void testGetPnrByIdWithEXception() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		when(pnrRepository.findById(11)).thenThrow(PnrNotFoundException.class);
		assertThrows(PnrNotFoundException.class, () -> pnrService.getPnrById(10));
		
	}

}
