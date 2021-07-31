package com.example.taskapp.config;

import com.example.taskapp.model.Account;
import com.example.taskapp.model.UserRole;
import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.example.taskapp.model.UserRole.*;

@RequiredArgsConstructor
@Component
public class AccountLoader implements CommandLineRunner {

  private final AccountRepository accountRepository;

  @Override
  //@Transactional

 /* public void run(String... args) throws Exception {
    Set<String> userRoles = new HashSet<>();
    userRoles.add("ROLE_USER");
    userRoles.add("ROLE_OPERATOR");
    accountRepository.save(new Account("name", "pass", userRoles));
  }*/

  public void run(String... args) throws Exception {
    Set<UserRole> userRoles = new HashSet<>();
    userRoles.add(ROLE_USER);
    userRoles.add(ROLE_OPERATOR);
    accountRepository.save(new Account("name", "pass", userRoles));
  }
}
