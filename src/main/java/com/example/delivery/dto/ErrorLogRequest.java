package com.example.delivery.dto;

import com.example.delivery.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorLogRequest {

    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("error_type")
    private ErrorType errorType;
    @JsonProperty("error_description")
    private String errorDescription;

    // Constructors
    public ErrorLogRequest() {
    }

    public ErrorLogRequest(String paymentId, ErrorType errorType, String errorDescription) {
        this.paymentId = paymentId;
        this.errorType = errorType;
        this.errorDescription = errorDescription;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
