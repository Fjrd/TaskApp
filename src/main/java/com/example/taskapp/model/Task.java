package com.example.taskapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.UUID;

@Data
@Entity
public class Task {

    @Id
    UUID id;
    TaskStatus status;
    String text;

    @ManyToOne
    User author;

}
