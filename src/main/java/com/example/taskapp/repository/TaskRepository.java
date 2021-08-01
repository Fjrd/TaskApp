package com.example.taskapp.repository;


import com.example.taskapp.model.Account;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  List<Task> findAllByAuthor(Account account);
  List<Task> findAllByStatus(TaskStatus status);
}
