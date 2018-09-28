package com.hiBalanceYes.controller;

import com.hiBalanceYes.model.Account;
import com.hiBalanceYes.model.Balance;
import com.hiBalanceYes.model.Transaction;
import com.hiBalanceYes.model.User;
import com.hiBalanceYes.repository.AccountRepository;
import com.hiBalanceYes.repository.BalanceRepository;
import com.hiBalanceYes.repository.TransactionRepository;
import com.hiBalanceYes.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private BalanceRepository balanceRepository;
    private AccountRepository accountRepository;

    public BalanceController(UserRepository userRepository, TransactionRepository transactionRepository, BalanceRepository balanceRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> getUserBalances(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(auth.getName());
        User user = userOptional.get();
        List<Balance> balances = balanceRepository.findAllByUserId(user.getId());

        return new ResponseEntity<>(balances,HttpStatus.OK);
    }

    @PostMapping("/fixedIncome")
    public ResponseEntity<?> fixedIncome(@RequestBody Balance balance){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(auth.getName());
        List<Balance> balances = new ArrayList<>();
        Transaction transaction = new Transaction();
        Optional <Account> account;

        balance.setType("fixedIncome");

        if (balance.getStartDate() == null){
            balance.setStartDate(new Date());
        }

        if (userOptional.get().getBalances() != null) {
            balances = userOptional.get().getBalances();
        }

        balances.add(balance);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setType("income");
        transaction.setCategory(balance.getTransaction().getCategory());
        transaction.setValue(balance.getValue());

        account = accountRepository.findById(balance.getAccount().getId());
        List<Transaction> accountListTransactions = account.get().getTransactions();
        accountListTransactions.add(transaction);
        account.get().setTransactions(accountListTransactions);

        balance.setAccount(account.get());
        balance.setTransaction(transactionRepository.save(transaction));
        balance.setUser(userOptional.get());

        userOptional.get().setBalances(balances);
        User user = userOptional.get();
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }
}
