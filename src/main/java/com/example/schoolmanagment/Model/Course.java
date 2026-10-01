package com.example.schoolmanagment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany
    private Set<Student> students;
}