package com.hiBalanceYes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  CardRepository extends JpaRepository<AccountRepository, Long> {
}