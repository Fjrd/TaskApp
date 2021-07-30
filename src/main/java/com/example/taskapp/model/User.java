package com.example.taskapp.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    UUID id;

    @ElementCollection
    Set<UserRole> roles;

    @OneToMany
    Set<Task> tasks;

}
