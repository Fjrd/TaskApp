package com.example.taskapp;

import com.example.taskapp.service.MessageConverter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class MessageConverterTest {
  MessageConverter messageConverter = new MessageConverter();

  @Test
  void convertTextTest(){
    var originalText = "Мне нужна помощь";
    var convertedText = messageConverter.convert(originalText, "", "-");
    assertEquals(convertedText, "М-н-е- -н-у-ж-н-а- -п-о-м-о-щ-ь");
    Function<String, String> consumer = s -> (s + "213");
  }
}
