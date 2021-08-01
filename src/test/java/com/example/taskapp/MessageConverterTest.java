package com.example.taskapp;

import com.example.taskapp.service.MessageConverter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class MessageConverterTest {
  MessageConverter messageConverter = new MessageConverter();

  @Test
  void convertTextTest(){
    String originalText = "Мне нужна помощь";
    String convertedText = messageConverter.convert(originalText, "", "-");
    Assertions.assertEquals(convertedText, "М-н-е- -н-у-ж-н-а- -п-о-м-о-щ-ь");
  }
}
