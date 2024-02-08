package com.example.delivery.service;

import com.example.delivery.dto.ErrorLogRequest;
import com.example.delivery.dto.PaymentRequest;
import com.example.delivery.enums.ErrorType;
import com.example.delivery.enums.PaymentType;
import com.example.delivery.feign.LogsClient;
import com.example.delivery.feign.PaymentValidationClient;
import com.example.delivery.model.Payment;
import com.example.delivery.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PaymentsListener {
    @Autowired
    private PaymentValidationClient paymentValidationClient;

    @Autowired
    private LogsClient logsClient;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    AccountRepository accountRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "online", groupId = "payment_processor_group")
    public void listenOnlinePayments(String message) {
        PaymentRequest paymentRequest = new PaymentRequest();
        try {
            paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
            ResponseEntity<?> response = paymentValidationClient.validatePayment(paymentRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                paymentService.processPayment(convertToPayment(paymentRequest));
            } else {
                logInvalidPayment(paymentRequest, ErrorType.OTHER, "Invalid payment");
            }
        } catch (JsonProcessingException e) {
            saveLogError("JSON parsing error: " + e.getMessage());
        } catch (DataAccessException e) {
            logInvalidPayment(paymentRequest, ErrorType.DATABASE, e.getMessage());
        } catch (Exception e) {
            saveLogError("Unexpected error during payment processing: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "offline", groupId = "payment_processor_group")
    public void listenOfflinePayments(String message) {
        try {
            PaymentRequest paymentRequest = objectMapper.readValue(message, PaymentRequest.class);

        } catch (Exception e) {
            saveLogError(e.getMessage());
        }
    }

    private Payment convertToPayment(PaymentRequest paymentRequest) {
        return new Payment(paymentRequest.getPaymentId(),
                accountRepository.getAccountByAccountId(paymentRequest.getAccountId()),
                PaymentType.valueOf(paymentRequest.getPaymentType().toUpperCase()),
                paymentRequest.getCreditCard(),
                paymentRequest.getAmount(),
                Date.from(Instant.now()));
    }

    private void logInvalidPayment(PaymentRequest paymentRequest, ErrorType errorType, String errorMessage) {
        ErrorLogRequest errorLog = new ErrorLogRequest(paymentRequest.getPaymentId(), errorType, errorMessage);
        logsClient.logError(errorLog);
    }

    private void saveLogError(String errorMessage) {
        ErrorLogRequest errorLog = new ErrorLogRequest("no payment_id", ErrorType.NETWORK, errorMessage);
        logsClient.logError(errorLog);
    }
}
