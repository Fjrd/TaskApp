package com.example.taskapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    @NonNull
    String name;

    @NonNull
    @ElementCollection
    Set<UserRole> roles;

    @OneToMany
    Set<Task> tasks;

}
