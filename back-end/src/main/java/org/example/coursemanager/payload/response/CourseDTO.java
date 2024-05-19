package org.example.coursemanager.payload.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.Subject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;

    @NotBlank(message = "image is mandatory")
    private String image;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "price is mandatory")
    private String price;

    @NotBlank(message = "short description is mandatory")
    private String shortDescription;

    @NotBlank(message = "lesson content is mandatory")
    private String lessonContent;


    private String requirement;

    @NotBlank(message = "description is mandatory")
    private String description;

//    @NotBlank(message = "subject is mandatory")
    private Set<Subject> subjects = new HashSet<>();

//    @NotBlank(message = "schedule is mandatory")
    private Set<Schedule> schedules = new HashSet<>();

    private Set<CourseClass> classes;
    private Date created;

}
