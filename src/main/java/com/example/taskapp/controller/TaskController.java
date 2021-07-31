package com.example.taskapp.controller;

import com.example.taskapp.TaskNotFoundException;
import com.example.taskapp.model.Task;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Secured("ROLE_OPERATOR")
    @GetMapping
    List<Task> all(){
        return taskRepository.findAll();
    }

    @PostMapping
    Task newTask(@RequestBody Task newTask){
        return taskRepository.save(newTask);
    }

    @GetMapping("/{id}")
    Task one(@PathVariable UUID id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

}
