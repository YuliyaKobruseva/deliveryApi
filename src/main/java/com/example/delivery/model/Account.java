package com.example.delivery.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String email;

    private Date birthdate;

    private String lastPaymentDate;

    @Column(nullable = false, updatable = false)
    private Date createdOn;

    public Account(Integer accountId, String email, Date birthdate, String lastPaymentDate, Date createdOn) {
        this.accountId = accountId;
        this.email = email;
        this.birthdate = birthdate;
        this.lastPaymentDate = lastPaymentDate;
        this.createdOn = createdOn;
    }


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Account() {
    }
}
