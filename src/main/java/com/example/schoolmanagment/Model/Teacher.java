package com.example.schoolmanagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "Age can't be null")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Wrong email format")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotNull(message = "Salary can't be null")
    @Positive(message = "Salary must be greater than 0")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}
