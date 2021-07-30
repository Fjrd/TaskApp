package com.example.taskapp.repository;

import com.example.taskapp.model.Task;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public class TaskRepository implements Repository<Task, UUID> {
}
