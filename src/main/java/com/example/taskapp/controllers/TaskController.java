package com.example.taskapp.controllers;

import com.example.taskapp.repositories.TaskRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    TaskRepository taskRepository;
}
