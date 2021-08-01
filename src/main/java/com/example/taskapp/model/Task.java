package com.example.taskapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@ToString
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Validated
@Jacksonized
public class Task {

  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
  UUID id;

  @NonNull
  TaskStatus status;

  @NonNull
  //@Max(value = 255)
  String text;

  @NonNull
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "author_id")
  @JsonManagedReference
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
