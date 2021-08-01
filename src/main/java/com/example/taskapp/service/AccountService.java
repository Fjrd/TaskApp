package com.example.taskapp.service;

import com.example.taskapp.dao.AccountDao;
import com.example.taskapp.model.Account;
import com.example.taskapp.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {
  AccountDao accountDao;

  public List<Account> findAll() {
    return accountDao.findAll();
  }

  public Account findById(String id) {
    return accountDao.findById(id);
  }

  public Account grantRoleOperator(String id) {
    Account oldAccount = accountDao.findById(id);
    Set<UserRole> roles = oldAccount.getRoles();
    roles.add(UserRole.ROLE_OPERATOR);
    Account newAccount = oldAccount
        .toBuilder()
        .roles(roles)
        .build();
    accountDao.save(newAccount);
    return newAccount;
  }
}
