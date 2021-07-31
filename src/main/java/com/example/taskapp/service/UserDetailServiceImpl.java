package com.example.taskapp.service;

import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository repository;


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
}
