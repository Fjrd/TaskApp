package com.example.taskapp;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException{
  public AccountNotFoundException(UUID id) {
    super("Could not find account " + id);
  }
}
