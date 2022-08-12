package com.pnr.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pnr.dto.Booking;
import com.pnr.dto.Train;
import com.pnr.pojo.Pnr;

@SpringBootTest
public class PnrRepositoryTest {
	
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
		assertEquals("confirmed",pnr.getPnrStatus());

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
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChaiClass");
		
		Train train = new Train();
		train.setTrainId(12);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");
		train.setDepartureTime("8:00");
		train.setArrivalTime("15:30");
		
		Optional<Pnr> optionalPnr = Optional.of(pnr);
		when(pnrRepository.findById(10)).thenReturn(optionalPnr);
		assertEquals("confirmed", pnr.getPnrStatus());
	}
}
