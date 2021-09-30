package com.example.taskapp.service;

import com.example.taskapp.AccountNotFoundException;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import com.example.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.taskapp.model.TaskStatus.*;
import static com.example.taskapp.model.UserRole.ROLE_OPERATOR;

@Service
@RequiredArgsConstructor
public class TaskService {

  TaskRepository taskRepository;
  UserDetailServiceImpl userDetailService;
  MessageConverter messageConverter;

  @Transactional
  public List<Task> findAllByAuthor(User user) {
    //TODO DTO
    return taskRepository.findAllByAuthor(userDetailService.loadCurrentLoggedAccountByName(user.getUsername()));
  }

  @Transactional
  public Task create(Task task) {
    if (task.getStatus() == DRAFT || task.getStatus() == SENT)
      taskRepository.save(task);
    return task;
    //TODO wrong status
  }

  @Transactional
  public Task findById(UUID id, User user) {
    Task task = taskRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    if (user.getAuthorities().contains(ROLE_OPERATOR)){
      String newMessage = messageConverter.convert(task.getText(), "", "-");
      return Task.builder().text(newMessage).build();
    }
    return task;
  }

  @Transactional
  public Task edit(Task task) {
    if (task.getStatus() == DRAFT)
      taskRepository.save(task);
    return taskRepository.findById(task.getId()).orElseThrow(() -> new AccountNotFoundException(task.getId()));
    //TODO wrong status
  }

  @Transactional
  public List<Task> findAllByStatus(TaskStatus status) {
    return taskRepository.findAllByStatus(status);
  }


  @Transactional
  public Task sendTask(String id) {
    UUID uuid = UUID.fromString(id);

    Task task = taskRepository.findById(uuid).orElseThrow(() -> new AccountNotFoundException(uuid));
    return taskRepository.save(task
        .toBuilder()
        .status(SENT)
        .build());
  }

  @Transactional
  public Task acceptTack(String id) {
    UUID uuid = UUID.fromString(id);

    Task task = taskRepository.findById(uuid).orElseThrow(() -> new AccountNotFoundException(uuid));
    return taskRepository.save(task
        .toBuilder()
        .status(ACCEPTED)
        .build());
  }

  @Transactional
  public Task rejectTask(String id) {
    UUID uuid = UUID.fromString(id);

    Task task = taskRepository.findById(uuid).orElseThrow(() -> new AccountNotFoundException(uuid));
    return taskRepository.save(task
        .toBuilder()
        .status(REJECTED)
        .build());
  }
}
