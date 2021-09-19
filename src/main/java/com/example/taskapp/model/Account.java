package com.example.taskapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@ToString
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Validated
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
  @Enumerated(value = EnumType.STRING)
  Set<UserRole> roles;

  @ToString.Exclude
  @OneToMany(mappedBy = "author")
  @JsonBackReference
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
