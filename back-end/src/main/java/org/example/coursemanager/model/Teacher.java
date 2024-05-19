package org.example.coursemanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private String phone;
    private String email;
    private String address;
    private int status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<CourseClass> classes;
}
