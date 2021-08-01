package com.example.taskapp.service;

import com.example.taskapp.dao.TaskDao;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.taskapp.model.TaskStatus.*;
import static com.example.taskapp.model.UserRole.ROLE_OPERATOR;

@Service
@RequiredArgsConstructor
public class TaskService {

  TaskDao taskDao;
  UserDetailServiceImpl userDetailService;
  MessageConverter messageConverter;

  public List<Task> findAllByAuthor(User user) {
    //TODO DTO
    return taskDao.findAllByAuthor(userDetailService.loadCurrentLoggedAccountByName(user.getUsername()));
  }

  public Task create(Task task) {
    if (task.getStatus() == DRAFT || task.getStatus() == SENT)
      taskDao.save(task);
    return task;
    //TODO throw exception wrong status
  }

  public Task findById(UUID id, User user) {
    Task task = taskDao.findById(id);
    if (user.getAuthorities().contains(ROLE_OPERATOR)){
      String newMessage = messageConverter.convert(task.getText(), "", "-");
      return Task.builder().text(newMessage).build();
    }
    return task;

  }

  public Task edit(Task task) {
    if (task.getStatus() == DRAFT)
      taskDao.save(task);
    return taskDao.findById(task.getId());
    //TODO throw exception wrong status
  }

  public List<Task> findAllByStatus(TaskStatus status) {
    return taskDao.findAllByStatus(status);
  }


  //TODO duplicate code
  public Task sendTask(String id) {
    return taskDao.save(taskDao.findById(UUID.fromString(id))
        .toBuilder()
        .status(SENT)
        .build());
  }

  public Task acceptTack(String id) {
    return taskDao.save(taskDao.findById(UUID.fromString(id))
        .toBuilder()
        .status(ACCEPTED)
        .build());
  }

  public Task rejectTask(String id) {
    return taskDao.save(taskDao.findById(UUID.fromString(id))
        .toBuilder()
        .status(REJECTED)
        .build());
  }
}
