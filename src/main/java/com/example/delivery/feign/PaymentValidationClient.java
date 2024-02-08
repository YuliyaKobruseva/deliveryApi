package com.example.delivery.feign;

import com.example.delivery.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentClient", url = "http://localhost:9000")
public interface PaymentValidationClient {
    @PostMapping("/payment")
    ResponseEntity<?> validatePayment(@RequestBody PaymentRequest paymentRequest);
}
