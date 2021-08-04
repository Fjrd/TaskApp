package com.example.taskapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ROLE_ADMIN")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class AccountControllerTest {

  MockMvc mockMvc;

  @NonFinal
  boolean isDbInitialized;

  @BeforeEach
  void setUp() {
      if(!isDbInitialized){
        isDbInitialized = true;
        //make users
      }
  }

  @Test
  @SneakyThrows
  @DisplayName("account controller works correctly")
  void accountControllerWorksCorrectlyTest() {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[1].roles").value("ROLE_OPERATOR"));

  }

}
