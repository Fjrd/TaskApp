package com.example.taskapp.service;

import com.example.taskapp.AccountNotFoundException;
import com.example.taskapp.model.Account;
import com.example.taskapp.model.UserRole;
import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
  AccountRepository accountRepository;

  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  public Account findById(String id) {
    UUID uuid = UUID.fromString(id);
    return accountRepository
        .findById(uuid)
        .orElseThrow(() -> new AccountNotFoundException(uuid));
  }

  public Account grantRoleOperator(String id) {
    UUID uuid = UUID.fromString(id);
    Account oldAccount = accountRepository.findById(uuid).orElseThrow(() -> new AccountNotFoundException(uuid));
    Set<UserRole> roles = oldAccount.getRoles();
    roles.add(UserRole.ROLE_OPERATOR);
    Account newAccount = oldAccount
        .toBuilder()
        .roles(roles)
        .build();
    accountRepository.save(newAccount);
    return newAccount;
  }
}
