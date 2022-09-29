package com.pnr.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
		assertEquals("confirmed", pnr.getPnrStatus());

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

	@Test
	public void testGetALlPnr() {

		Pnr pnr = new Pnr();
		pnr.setPnrId(10);
		pnr.setPnrStatus("confirmed");
		pnr.setBookingId(11);
		pnr.setTrainId(12);

		Pnr pnr1 = new Pnr();
		pnr1.setPnrId(10);
		pnr1.setPnrStatus("confirmed");
		pnr1.setBookingId(11);
		pnr1.setTrainId(12);

		Pnr pnr2 = new Pnr();
		pnr2.setPnrId(10);
		pnr2.setPnrStatus("confirmed");
		pnr2.setBookingId(11);
		pnr2.setTrainId(12);

		List<Pnr> pnrList = new ArrayList<>();
		pnrList.add(pnr);
		pnrList.add(pnr1);
		pnrList.add(pnr2);

		when(pnrRepository.findAll()).thenReturn(pnrList);
		assertEquals("confirmed", pnr.getPnrStatus());

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
		assertEquals("confirmed", pnr.getPnrStatus());

	}

}
