package com.paymentservice.serviceimpl;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public Payment doPay(Payment payment) {
		payment.setPaymentStatus(paymentStatus());
		payment.setTxId(UUID.randomUUID().toString());
		
		return paymentRepo.save(payment);
	}

	private String paymentStatus() {
		return new Random().nextBoolean()?"success":"failure";
	}
}
