package com.pnr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pnr.dto.PnrResponse;
import com.pnr.pojo.Pnr;
import com.pnr.service.PnrService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PnrController {
	
	Logger logger = LoggerFactory.getLogger(PnrController.class);
	
	@Autowired
	private PnrService pnrService;
	
	@PostMapping("/pnr/save")
	public ResponseEntity<Pnr> addPnr(@RequestBody Pnr pnr) {
		
		logger.info("addPnr method from Pnr controller is accessed");
		
		Pnr newPnr = pnrService.savePnr(pnr);
		ResponseEntity<Pnr> responseEntity = new ResponseEntity<>(newPnr, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/pnr/byid/{pId}")
	public ResponseEntity<PnrResponse> getPnrById(@PathVariable("pId") int pnrId) {
		
		logger.info("PnrById method from Pnr controller is accessed");

		PnrResponse pnrResponse = pnrService.getPnrByBookingId(pnrId);
		ResponseEntity<PnrResponse> responseEntity = new ResponseEntity<>(pnrResponse, HttpStatus.OK);
		return responseEntity;

	}
	
	@GetMapping("/pnr/pnrById/{pnrId}")
	public ResponseEntity<Pnr> getPnrStatusById(@PathVariable("pnrId") int pnrId) {
		
		logger.info("PnrStatusById method from Pnr controller is accessed");
		
		Pnr pnrById = pnrService.getPnrById(pnrId);
		ResponseEntity<Pnr> responseEntity = new ResponseEntity<>(pnrById, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/pnr/delete/{pnrId}")
	public ResponseEntity<String> cancelPnr(@PathVariable("pnrId") int pnrId) {
		
		logger.info("cancelPNR method from Pnr controller is accessed");

		pnrService.deletePnr(pnrId);
		return new ResponseEntity<>("PNR cancel successfully", HttpStatus.OK);
	}
	
	@GetMapping("/pnr/all")
	public List<Pnr> fetchAllPnr() {
		
		logger.info("fectAllPnr method from Pnr controller is accessed");
		
		List<Pnr> allPnr = pnrService.getAllPnr();
		return allPnr;
	}
	
	@PutMapping("/pnr/update")
	public ResponseEntity<Pnr> updatePnr(@RequestBody Pnr pnr) {
		
		logger.info("updatePnr method from Pnr controller is accessed");
		
		Pnr updatedPnr = pnrService.updatePnr(pnr);
		ResponseEntity<Pnr> responseEntity = new ResponseEntity<>(updatedPnr, HttpStatus.OK);
		return responseEntity;
	}

}
