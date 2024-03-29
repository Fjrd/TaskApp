package com.example.taskapp.service;

import com.example.taskapp.model.Account;
import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

    return repository.findByName(name)
        .map(account -> new User(
            name,
            account.getPassword(),
            account
                .getRoles()
                .stream()
                .map(s -> new SimpleGrantedAuthority(s.name()))
                .collect(Collectors.toList())))
        .orElseThrow(() -> new UsernameNotFoundException(name));
  }

  public Account loadCurrentLoggedAccountByName(String name) {
    return repository.findByName(name)
        .orElseThrow(() -> new UsernameNotFoundException(name));
  }
}
