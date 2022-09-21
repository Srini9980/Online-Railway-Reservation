package com.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;
import com.paymentservice.serviceimpl.SequenceGeneratorService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@PostMapping("/doPay")
	public Payment doPayment(@RequestBody Payment payment) {
		payment.setPaymentId(sequenceGeneratorService.generateSequence(Payment.SEQUENCE_NAME));
		return service.doPay(payment);
	}
}
