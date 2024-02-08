package com.example.delivery.dto;

import com.example.delivery.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {
    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("payment_type")
    private String paymentType;
    @JsonProperty("credit_card")
    private String creditCard;
    private Integer amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String paymentId, Integer accountId, String paymentType, String creditCard, Integer amount) {
        this.paymentId = paymentId;
        this.accountId = accountId;
        this.paymentType = paymentType;
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
