package com.ticket.service;

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

import com.ticket.dto.BookingResponse;
import com.ticket.dto.Train;
import com.ticket.exception.BookingNotFoundException;
import com.ticket.pojo.Booking;
import com.ticket.repository.BookingRepository;

@SpringBootTest
public class BookingServiceTest {

	@InjectMocks
	private BookingService bookingService = new BookingServiceImpl();

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private BookingRepository bookingRepository;

	@Test
	public void testSaveBooking() {

		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setPassengerName("srinivas");
		booking.setAge(25);
		booking.setGender("male");
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);

		Train train = new Train();
		train.setTrainId(11);
		train.setTrainName("double-decker");
		train.setSource("bangalore");
		train.setDestination("chennai");

		when(bookingRepository.save(booking)).thenReturn(booking);
		BookingResponse newBooking = bookingService.saveBooking(booking);
		newBooking.setBooking(booking);
		newBooking.setTrain(train);
		assertEquals(booking, newBooking.getBooking());
		verify(bookingRepository, times(1)).save(booking);
	}

	@Test
	public void testGetAllBooking() {

		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setPassengerName("srinivas");
		booking.setAge(25);
		booking.setGender("male");
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);

		Booking booking1 = new Booking();
		booking1.setBookingId(10);
		booking1.setPassengerName("srinivas");
		booking1.setAge(25);
		booking1.setGender("male");
		booking1.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking1.setSeatType("aCChairClass");
		booking1.setTrainId(11);

		Booking booking2 = new Booking();
		booking2.setBookingId(10);
		booking2.setPassengerName("srinivas");
		booking2.setAge(25);
		booking2.setGender("male");
		booking2.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking2.setSeatType("aCChairClass");
		booking2.setTrainId(11);

		List<Booking> allBooking = new ArrayList<>();
		allBooking.add(booking);
		allBooking.add(booking1);
		allBooking.add(booking2);

		when(bookingRepository.findAll()).thenReturn(allBooking);
		List<Booking> bookings = bookingService.getAllBooking();
		assertEquals(3, bookings.size());
	}

	@Test
	public void testDeleteBooking() {

		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setPassengerName("srinivas");
		booking.setAge(25);
		booking.setGender("male");
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);

		Optional<Booking> optionalBooking = Optional.of(booking);
		when(bookingRepository.findById(10)).thenReturn(optionalBooking);
		bookingService.deleteBooking(10);
		verify(bookingRepository, times(1)).findById(10);
		verify(bookingRepository, times(1)).deleteById(10);
	}

	@Test
	public void testDeleteBookingByIdWithException() {

		when(bookingRepository.findById(10)).thenThrow(BookingNotFoundException.class);
		assertThrows(BookingNotFoundException.class, () -> bookingService.deleteBooking(10));
	}

	@Test
	public void testGetBookingById() {

		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setPassengerName("srinivas");
		booking.setAge(25);
		booking.setGender("male");
		booking.setDateOfJourney(LocalDate.of(2022, 12, 31));
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);

		Optional<Booking> optionalBooking = Optional.of(booking);
		when(bookingRepository.findById(10)).thenReturn(optionalBooking);
		Booking myBooking = bookingService.getBookingById(10);
		assertEquals("srinivas", myBooking.getPassengerName());
	}

	@Test
	public void testGetBookingByIdWithException() {

		when(bookingRepository.findById(10)).thenThrow(BookingNotFoundException.class);
		assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingById(10));
	}

}
