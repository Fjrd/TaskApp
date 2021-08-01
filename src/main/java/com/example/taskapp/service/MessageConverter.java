package com.example.taskapp.service;

import org.springframework.stereotype.Service;

@Service
public class MessageConverter {
  public String convert(String message, String from, String to){
      return message.replace(from, to).substring(1, message.length() * 2);
    }
}
