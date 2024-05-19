package org.example.coursemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "image is mandatory")
    private String image;

    @NotBlank(message = "name is mandatory")
    private String name;

    @Positive(message = "price is invalid")
    private int price;

    @Column(columnDefinition = "LONGTEXT")
    @NotBlank(message = "short description is mandatory")
    private String shortDescription;

    @Column(columnDefinition = "LONGTEXT")
    private String lessonContent;

    @Column(columnDefinition = "LONGTEXT")
    private String requirement;

    @Column(columnDefinition = "LONGTEXT")
    @NotBlank(message = "description is mandatory")
    private String description;



    @ManyToMany
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    private Set<Schedule> schedules = new HashSet<>();

//    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    private Set<CourseClass> classes;



    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }


}
