package com.hiBalanceYes.controller;

import com.hiBalanceYes.model.Card;
import com.hiBalanceYes.model.User;
import com.hiBalanceYes.repository.AccountRepository;
import com.hiBalanceYes.repository.CardRepository;
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

import java.util.Optional;

@RestController
@RequestMapping("users/cards")
public class CardController {
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    public CardController(AccountRepository accountRepository, UserRepository userRepository, CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> createUserCard(@RequestBody Card card){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(auth.getName());
        User user = userOptional.get();
        

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
