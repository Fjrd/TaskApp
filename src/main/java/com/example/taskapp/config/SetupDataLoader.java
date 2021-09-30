package com.example.taskapp.config;

import com.example.taskapp.model.Account;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import com.example.taskapp.repository.AccountRepository;
import com.example.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.example.taskapp.model.UserRole.*;

@Component
@RequiredArgsConstructor
public class SetupDataLoader  implements ApplicationListener<ContextRefreshedEvent> {

  AccountRepository accountRepository;
  TaskRepository taskRepository;

  @NonFinal
  boolean alreadySetup = false;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {

    Account userAcc = Account.builder()
        .name("user")
        .password("user")
        .roles(new HashSet<>(Arrays.asList(ROLE_USER)))
        .build();

    Account operatorAcc = Account.builder()
        .name("operator")
        .password("operator")
        .roles(new HashSet<>(Arrays.asList(ROLE_OPERATOR)))
        .build();

    Account adminAcc = Account.builder()
        .name("admin")
        .password("admin")
        .roles(new HashSet<>(Arrays.asList(ROLE_ADMIN)))
        .build();

    List.of(
        userAcc,
        operatorAcc,
        adminAcc)
          .forEach(accountRepository::save);


    List.of(
      Task.builder().status(TaskStatus.DRAFT).text("asdf").author(userAcc).build(),
      Task.builder().status(TaskStatus.SENT).text("erger").author(userAcc).build(),
      Task.builder().status(TaskStatus.DRAFT).text("sdgs").author(operatorAcc).build(),
      Task.builder().status(TaskStatus.SENT).text("1231asdas2").author(operatorAcc).build(),
      Task.builder().status(TaskStatus.ACCEPTED).text("1231gfdf2").author(operatorAcc).build(),
      Task.builder().status(TaskStatus.REJECTED).text("1231gfdfgdf2").author(adminAcc).build())
        .forEach(taskRepository::save);

    alreadySetup = true;
  }
}
