package com.hiBalanceYes.repository;

import com.hiBalanceYes.model.Card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  CardRepository extends JpaRepository<Card, Long> {
}
