package com.example.taskapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  UserDetailsService userDetailsService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .userDetailsService(userDetailsService)
        .authorizeRequests()
        .antMatchers("/h2-console/**",
            "/swagger-ui.html", //doesn't work
            "/swagger-resources/**")
        .permitAll();

    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();
    httpSecurity.authorizeRequests().anyRequest().authenticated();
    httpSecurity.formLogin();
  }
}
