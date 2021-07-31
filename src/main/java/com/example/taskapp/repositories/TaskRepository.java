package com.example.taskapp.repositories;

import com.example.taskapp.model.Task;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public class TaskRepository implements Repository<Task, UUID> {
}
