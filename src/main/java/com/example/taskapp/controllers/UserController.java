package com.example.taskapp.controllers;

import com.example.taskapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserRepository userRepository;

}
