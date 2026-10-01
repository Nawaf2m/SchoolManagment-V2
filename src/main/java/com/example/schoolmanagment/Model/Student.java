package com.example.schoolmanagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotNull(message = "Age can't be empty")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Major can't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}