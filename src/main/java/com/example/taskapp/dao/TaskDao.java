package com.example.taskapp.dao;

import com.example.taskapp.model.Account;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import com.example.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskDao implements GenericDao<Task>{
  TaskRepository taskRepository;

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  public List<Task> findAllByAuthor(Account account) {
    return taskRepository.findAllByAuthor(account);
  }

  public Task findById(UUID id) {
    return taskRepository.findById(id).get();
  }

  public List<Task> findAllByStatus(TaskStatus status) {
    return taskRepository.findAllByStatus(status);
  }
}
