package com.example.taskapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    @NonNull
    String name;

    @NonNull
    String password;

    /*
    @NonNull
    @ElementCollection
    Set<UserRole> roles;
    */
    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    Set<UserRole> roles;

    @OneToMany
    Set<Task> tasks;

}
