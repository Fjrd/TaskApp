package com.example.taskapp.controller;

import com.example.taskapp.AccountNotFoundException;
import com.example.taskapp.model.Account;
import com.example.taskapp.repository.AccountRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    List<Account> all(){
      return accountRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    Account one(@PathVariable UUID id){
      return accountRepository.findById(id)
          .orElseThrow(() -> new AccountNotFoundException(id));
    }

/*
    @Secured("ROLE_ADMIN")
    @PatchMapping("/{id}")
    ResponseEntity<Account> changeOne(@PathVariable UUID id, @RequestBody Map<Object, Object> updates){

    }
*/
}
