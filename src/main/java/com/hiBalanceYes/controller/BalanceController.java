package com.hiBalanceYes.controller;

import com.hiBalanceYes.model.Balance;
import com.hiBalanceYes.model.Transaction;
import com.hiBalanceYes.model.User;
import com.hiBalanceYes.repository.TransactionRepository;
import com.hiBalanceYes.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/balances")
public class BalanceController {
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

    public BalanceController(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/fixedIncome")
    public ResponseEntity<?> fixedIncome(@RequestBody Balance balance){
        User user = (User) auth.getPrincipal();
        List<Balance> balances = new ArrayList<>();
        Transaction transaction = new Transaction();

        balance.setType("fixedIncome");

        if (balance.getStartDate() == null){
            balance.setStartDate(new Date());
        }

        if (user.getBalances() != null) {
            balances = user.getBalances();
        }

        balances.add(balance);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setType("income");
        transaction.setCategory(balance.getTransaction().getCategory());
        transaction.setValue(balance.getValue());

        transactionRepository.save(transaction);

        user.setBalances(balances);


        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }
}
