package com.example.delivery.repository;

import com.example.delivery.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account getAccountByAccountId(int accountId);
}
