package com.example.taskapp.controllers;

import com.example.taskapp.model.Task;
import com.example.taskapp.repositories.TaskRepository;
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
