package com.example.delivery.service;

import com.example.delivery.model.Account;
import com.example.delivery.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(int id) {
        return accountRepository.getAccountByAccountId(id);
    }
}
