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

    //TODO mode account and task init to tests?
    Account account1 = new Account("name", "pass", new HashSet<>(Arrays.asList(ROLE_USER, ROLE_OPERATOR, ROLE_ADMIN)));
    Account account2 = new Account("name2", "pass2", new HashSet<>(Arrays.asList(ROLE_USER, ROLE_OPERATOR, ROLE_ADMIN)));
    accountRepository.save(account1);
    accountRepository.save(account2);
    taskRepository.save(new Task(TaskStatus.DRAFT, "text", account1));
    taskRepository.save(new Task(TaskStatus.DRAFT, "text1", account2));
    taskRepository.save(new Task(TaskStatus.SENT, "text2", account1));
    taskRepository.save(new Task(TaskStatus.SENT, "text3", account2));
    taskRepository.save(new Task(TaskStatus.ACCEPTED, "text4", account1));
    taskRepository.save(new Task(TaskStatus.REJECTED, "text5", account2));
  }
}
