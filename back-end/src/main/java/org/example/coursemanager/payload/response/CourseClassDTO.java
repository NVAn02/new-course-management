package org.example.coursemanager.payload.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.Teacher;
import org.example.coursemanager.model.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseClassDTO {

    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    private String openingDay;

    private Teacher teacher;

    @NotNull(message = "Schedule is mandatory")
    private Schedule schedule;

    private Course course;

    private Set<User> students = new HashSet<>();
    private int status;
}
