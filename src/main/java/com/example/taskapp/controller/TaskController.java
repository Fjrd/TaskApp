package com.example.taskapp.controller;

import com.example.taskapp.TaskNotFoundException;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskStatus;
import com.example.taskapp.repository.TaskRepository;
import com.example.taskapp.service.UserDetailServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserDetailServiceImpl userDetailService;

    public TaskController(TaskRepository taskRepository, UserDetailServiceImpl userDetailService) {
      this.taskRepository = taskRepository;
      this.userDetailService = userDetailService;
    }

    @Secured("ROLE_USER")
    //TODO make correct path
    @GetMapping("me")
    List<Task> allTasksCreatedByUser(@AuthenticationPrincipal User user){
        return taskRepository.findAllByAuthor(userDetailService.loadCurrentLoggedAccountByName(user.getUsername()));
    }

    //TODO does in work? how to postman
    @Secured("ROLE_USER")
    @PostMapping
    Task newTask(@RequestBody Task newTask){
        return taskRepository.save(newTask);
    }

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    Task one(@PathVariable UUID id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    @Secured("ROLE_OPERATOR")
    @GetMapping("sent")
    List<Task> allSentTask(){
      return taskRepository.findAllByStatus(TaskStatus.SENT);
    }


    //TODO user can edit task in DRAFT status
    //TODO user can send task to operator
    //TODO operator can accept and reject tasks (change status)

}
