package com.example.taskapp.controller;

import com.example.taskapp.model.Account;
import com.example.taskapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class AccountController {
    AccountService accountService;


    @Secured("ROLE_ADMIN")
    @GetMapping
    List<Account> all(){
      return accountService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("{id}")
    Account one(@PathVariable String id){
      return accountService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("{id}/operator")
    Account grantRoleOperator(@PathVariable String id){
      return accountService.grantRoleOperator(id);
    }
}
