package com.example.taskapp.repository;

import com.example.taskapp.model.Account;
import com.example.taskapp.model.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class AccountRepositoryTest {

  AccountRepository accountRepository;

  @Test
  @SneakyThrows
  @DisplayName(" create user")
  void createUserTest() {

    Optional<Account> user = accountRepository.findByName("user");

    assertThat(user)
        .isNotNull()
        .isNotEmpty()
        .get()
        .matches(account -> account.getName().equals("user"))
        .matches(account -> account.getPassword().equals("user"));

    assertThat(user.get().getRoles())
        .isNotNull()
        .hasSize(1)
        .containsExactly(UserRole.valueOf("ROLE_USER"));
  }
}
