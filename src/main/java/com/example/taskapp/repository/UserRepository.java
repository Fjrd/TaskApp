package com.example.taskapp.repository;

import com.example.taskapp.model.User;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public class UserRepository implements Repository<User, UUID> {

}
