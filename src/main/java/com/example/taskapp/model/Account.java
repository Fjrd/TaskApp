package com.example.taskapp.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Validated
@Builder(toBuilder = true)
public class Account {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    @NonNull
    String name;

    @NonNull
    String password;

  @NonNull
  @ElementCollection(fetch = FetchType.EAGER)
  Set<UserRole> roles;

  @OneToMany
  @JoinColumn(name = "id")
  @ToString.Exclude
  Set<Task> tasks;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Account account = (Account) o;

    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return 2083479647;
  }
}
