package com.hiBalanceYes.controller;

import com.hiBalanceYes.model.Account;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/accounts")
public class AccountController {
    private UserRepository userRepository;
    private BalanceRepository balanceRepository;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public AccountController(UserRepository userRepository, BalanceRepository balanceRepository, TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> createUserAccount(@RequestBody Account account){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(auth.getName());
        User user = userOptional.get();
        List<Account> accounts = user.getAccounts();
        accounts.add(account);
        user.setAccounts(accounts);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
