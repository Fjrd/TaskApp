package com.example.taskapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    UUID id;

    @NonNull
    TaskStatus status;

    @NonNull
    String text;

    @NonNull
    @ManyToOne
    User author;

}
