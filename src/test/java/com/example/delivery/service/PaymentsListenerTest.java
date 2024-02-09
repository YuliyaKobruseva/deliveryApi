package com.example.delivery.service;

import com.example.delivery.enums.PaymentType;
import com.example.delivery.feign.LogsClient;
import com.example.delivery.feign.PaymentValidationClient;
import com.example.delivery.model.Payment;
import com.example.delivery.utils.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentsListenerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentValidationClient paymentValidationClient;

    @MockBean
    private LogsClient logsClient;


    @Test
    void givenValidOnlinePayment_whenMessageIsReceived_thenVerifyProcessing() throws Exception {
        String paymentJson = JsonReader.loadJsonFromFile("json/validPayment.json");
        simulateKafkaMessageReception(PaymentType.ONLINE.toString().toLowerCase(), paymentJson);
        mockPaymentValidationClientToReturnSuccess();
        Payment payment = objectMapper.readValue(paymentJson, Payment.class);

        paymentService.processPayment(payment);

        verifyOfflinePaymentProcessing(payment);
        verifyNoErrorLogsSend();

    }

    @Test
    void givenOfflinePaymentMessage_whenReceived_thenSuccessfullyProcessed() throws IOException {
        String offlinePaymentJson = JsonReader.loadJsonFromFile("json/validPayment.json");
        simulateKafkaMessageReception(PaymentType.OFFLINE.toString().toLowerCase(), offlinePaymentJson);
        Payment payment = objectMapper.readValue(offlinePaymentJson, Payment.class);

        paymentService.processPayment(payment);

        verifyOfflinePaymentProcessing(payment);
        verifyNoErrorLogsSend();

    }

    private void simulateKafkaMessageReception(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    private void mockPaymentValidationClientToReturnSuccess() {
        when(paymentValidationClient.validatePayment(any()))
                .thenReturn(ResponseEntity.ok().build());
    }

    private void verifyOfflinePaymentProcessing(Payment payment) {
        verify(paymentService, times(1)).processPayment(payment);
    }

    private void verifyNoErrorLogsSend() {
        verify(logsClient, never()).logError(any());
    }

}
