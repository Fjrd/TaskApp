package com.example.taskapp.controllers;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(UUID id) {
        super("Could not find task " + id);
    }
}
