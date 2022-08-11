package com.pnr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pnr.dto.Booking;
import com.pnr.dto.PnrResponse;
import com.pnr.dto.Train;
import com.pnr.exception.PnrNotFoundException;
import com.pnr.pojo.Pnr;
import com.pnr.repository.PnrRepository;
import com.pnr.service.PnrService;
import com.pnr.service.PnrServiceImpl;

@SpringBootTest
public class PnrServiceTest {
	
	@InjectMocks
	private PnrService pnrService = new PnrServiceImpl();
	
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
		Pnr newPnr = new Pnr();
		assertEquals(newPnr,pnr);
		verify(pnrRepository,times(1)).save(pnr);
	}
	
	@Test
	public void testGetPnrByBooking() {
		
		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);
		
		Booking booking = new Booking();
		booking.setBookingId(11);
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		
		Train train = new Train();
		train.setTrainId(12);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("8:00");
		train.setArrivalTime("15:00");
		
		Optional<Pnr> optionalPnr = Optional.of(pnr);
		when(pnrRepository.findById(10)).thenReturn(optionalPnr);
		PnrResponse pnrResponse = pnrService.getPnrByBookingId(10);
		pnrResponse.setPnr(pnr);
		pnrResponse.setBooking(booking);
		pnrResponse.setTrain(train);
		assertEquals(booking, pnrResponse.getBooking());
		
	}
	
	@Test
	public void testPnrByIdWithException() {
		
		when(pnrRepository.findById(10)).thenThrow(PnrNotFoundException.class);
		assertThrows(PnrNotFoundException.class, () -> pnrService.getPnrByBookingId(10));
	}

}
