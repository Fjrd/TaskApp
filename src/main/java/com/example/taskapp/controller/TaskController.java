package com.example.taskapp.controller;

import com.example.taskapp.model.Task;
import com.example.taskapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.example.taskapp.model.TaskStatus.SENT;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
  TaskService taskService;

  @Secured("ROLE_USER")
  @GetMapping("my-tasks")
  public List<Task> allTasksCreatedByUser(@AuthenticationPrincipal User user) {
    return taskService.findAllByAuthor(user);
  }

  @Secured("ROLE_USER")
  @PostMapping
  public Task newTask(@RequestBody @Valid Task task){
      return taskService.create(task);
  }

  @Secured({"ROLE_USER", "ROLE_OPERATOR"})
  @GetMapping("{id}")
  Task one(@PathVariable UUID id, @AuthenticationPrincipal User user) {
    return taskService.findById(id, user);
  }

  @Secured("ROLE_USER")
  @PutMapping
  Task editTask(@RequestBody @Valid Task task) {
    return taskService.edit(task);
  }

  @Secured("ROLE_USER")
  @PutMapping("{id}/send")
  Task sendTask(@PathVariable String id) {
    return taskService.sendTask(id);
  }

  @Secured("ROLE_OPERATOR")
  @GetMapping("sent")
  List<Task> allSentTask() {
    return taskService.findAllByStatus(SENT);
  }

  @Secured("ROLE_OPERATOR")
  @PutMapping("{id}/accept")
  Task acceptTack(@PathVariable String id) {
    return taskService.acceptTack(id);
  }

  @Secured("ROLE_OPERATOR")
  @PutMapping("{id}/reject")
  Task rejectTask(@PathVariable String id) {
    return taskService.rejectTask(id);
  }


  //TODO PATCH requests

}
