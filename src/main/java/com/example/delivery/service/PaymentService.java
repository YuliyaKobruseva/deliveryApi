package com.example.delivery.service;

import com.example.delivery.model.Payment;
import com.example.delivery.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void processPayment(Payment payment) {

        paymentRepository.save(payment);
    }
}
