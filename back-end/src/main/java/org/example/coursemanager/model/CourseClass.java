package org.example.coursemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    @NotBlank(message = "name is mandatory")
    String name;

    String openingDay;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    Schedule schedule;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    Course course;
    int status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    Set<CourseRegister> courseRegisters;




//    Long courseId;
//@ManyToOne
//@JoinColumn(name="course_id", nullable=false)
//private Course course;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(  name = "user_classes",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> students = new HashSet<>();
}
