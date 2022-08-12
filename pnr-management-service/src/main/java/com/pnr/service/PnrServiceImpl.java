package com.pnr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pnr.dto.Booking;
import com.pnr.dto.PnrResponse;
import com.pnr.dto.Train;
import com.pnr.exception.PnrIdAlreadyExistsException;
import com.pnr.exception.PnrNotFoundException;
import com.pnr.pojo.Pnr;
import com.pnr.repository.PnrRepository;

@Service
public class PnrServiceImpl implements PnrService {

	@Autowired
	private PnrRepository pnrRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Pnr savePnr(Pnr pnr) {

		Optional<Pnr> pnrById = pnrRepository.findById(pnr.getPnrId());
		if (pnrById.isPresent()) {
			throw new PnrIdAlreadyExistsException("PNR number already exists ");
		}

		Pnr newPnr = pnrRepository.save(pnr);
		return newPnr;
	}

	@Override
	public PnrResponse getPnrByBookingId(int pnrId) {

		PnrResponse pnrResponse = new PnrResponse();
		Optional<Pnr> optionalPnr = pnrRepository.findById(pnrId);
		if (optionalPnr.isEmpty()) {
			throw new PnrNotFoundException("No pnr available with this Id : " + pnrId);
		}

		Pnr pnr = optionalPnr.get();
		Train train = restTemplate.getForObject("http://TRAIN-MANAGEMENT-SERVICE/train/find/" + pnr.getTrainId(), Train.class);
//		if (train.getTrainId() != pnr.getTrainId()) {
//			throw new PnrNotFoundException("No train booking done on this Id");
//		}

		Booking booking = restTemplate.getForObject("http://TICKET-MANAGEMENT-SERVICE/booking/find/" + pnr.getBookingId(), Booking.class);
//		if (booking.getBookingId() != pnr.getBookingId()) {
//			throw new PnrNotFoundException("No booking done on this Id");
//		}
		pnrResponse.setBooking(booking);
		pnrResponse.setTrain(train);
		pnrResponse.setPnr(pnr);
		return pnrResponse;
	}

}
