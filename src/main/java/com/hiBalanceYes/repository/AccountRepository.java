package com.hiBalanceYes.repository;

import com.hiBalanceYes.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends  JpaRepository<Account, Long> {
}
