package com.example.taskapp.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Jacksonized
public class Task {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    @NonNull
    TaskStatus status;

  @NonNull
  @Max(value = 255)
  String text;

  @JoinColumn(unique = true)
  @NonNull
  @ManyToOne
  Account author;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Task task = (Task) o;

    return Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return 1976597858;
  }
}
