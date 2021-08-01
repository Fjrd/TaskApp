package com.example.taskapp.dao;

import com.example.taskapp.model.Account;
import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountDao implements GenericDao<Account> {

  private AccountRepository accountRepository;

  @Override
  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Account findById(String id) {
    return accountRepository.findById(UUID.fromString(id)).get();
  }
}
