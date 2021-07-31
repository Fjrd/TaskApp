package com.example.taskapp.repositories;

import com.example.taskapp.model.User;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public class UserRepository implements Repository<User, UUID> {
}
