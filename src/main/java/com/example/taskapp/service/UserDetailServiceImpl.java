package com.example.taskapp.service;

import com.example.taskapp.model.Account;
import com.example.taskapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final AccountRepository repository;
  private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//    private static final Function<Account, User> TO_USER =
//        Function3.<String, String, Collection<? extends GrantedAuthority>, User>of(User::new)
//            .tupled()
//            .<Tuple3<String, String, ? extends GrantedAuthority>>compose(t -> t.map3(Collections::singleton))
//            .<Tuple3<String, String, String>>compose(t -> t.map3(SimpleGrantedAuthority::new))
//            .compose((Account account) -> Tuple.of(account.getName(), account.getPassword(), account.getRoles()));


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
    Account account = repository.findByName(name)
        .orElseThrow(() -> new UsernameNotFoundException(name));
    return account;
  }


}
