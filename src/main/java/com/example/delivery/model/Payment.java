package com.example.delivery.model;

import com.example.delivery.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @JsonProperty("payment_id")
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @JsonProperty("payment_type")
    private PaymentType paymentType;

    @JsonProperty("credit_card")
    private String creditCard;

    private Integer amount;

    @Column(nullable = false, updatable = false)
    @JsonProperty("created_on")
    private Date createdOn;

    public Payment() {
    }

    public Payment(String paymentId, Account account, PaymentType paymentType, String creditCard, Integer amount, Date createdOn) {
        this.paymentId = paymentId;
        this.account = account;
        this.paymentType = paymentType;
        this.creditCard = creditCard;
        this.amount = amount;
        this.createdOn = createdOn;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
