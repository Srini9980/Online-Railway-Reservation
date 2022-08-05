package com.pnr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pnr.dto.PnrResponse;
import com.pnr.pojo.Pnr;
import com.pnr.service.PnrService;

@RestController
public class PnrController {
	
	@Autowired
	private PnrService pnrService;
	
	@PostMapping("/pnr/save")
	public ResponseEntity<Pnr> addPnr(@RequestBody Pnr pnr) {
		Pnr newPnr = pnrService.savePnr(pnr);
		ResponseEntity<Pnr> responseEntity = new ResponseEntity<>(newPnr, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/pnr/byid/{pId}")
	public ResponseEntity<PnrResponse> getPnrById(@PathVariable("pId") int pnrId) {

		PnrResponse pnrResponse = pnrService.getPnrByBookingId(pnrId);
		ResponseEntity<PnrResponse> responseEntity = new ResponseEntity<>(pnrResponse, HttpStatus.OK);
		return responseEntity;

	}

}
