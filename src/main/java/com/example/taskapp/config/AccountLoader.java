package com.example.taskapp.config;

import com.example.taskapp.model.Account;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import com.example.taskapp.repository.AccountRepository;
import com.example.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

import static com.example.taskapp.model.UserRole.*;

@RequiredArgsConstructor
@Component
public class AccountLoader implements CommandLineRunner {

  private final AccountRepository accountRepository;
  private final TaskRepository taskRepository;

  @Override
  public void run(String... args) throws Exception {

   // TODO TESTS
    Account account1 = new Account("name", "pass", new HashSet<>(Arrays.asList(ROLE_USER, ROLE_OPERATOR, ROLE_ADMIN)));
    Account account2 = new Account("name2", "pass2", new HashSet<>(Arrays.asList(ROLE_USER, ROLE_OPERATOR, ROLE_ADMIN)));
    accountRepository.save(account1);
    accountRepository.save(account2);
    taskRepository.save(Task.builder().status(TaskStatus.DRAFT).text("asdf").author(account1).build());
    taskRepository.save(Task.builder().status(TaskStatus.REJECTED).text("sdgs").author(account2).build());
    taskRepository.save(Task.builder().status(TaskStatus.ACCEPTED).text("erger").author(account1).build());
    taskRepository.save(Task.builder().status(TaskStatus.DRAFT).text("1231asdas2").author(account2).build());
    taskRepository.save(Task.builder().status(TaskStatus.SENT).text("1231gfdf2").author(account1).build());
    taskRepository.save(Task.builder().status(TaskStatus.SENT).text("1231gfdfgdf2").author(account2).build());
  }
}
