package org.example.coursemanager.payload.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.coursemanager.model.CourseClass;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherDTO {

    private Long id;

    @NotNull(message = "teacher name is mandatory")
    private String name;

    private String phone;
    private String email;
    private String address;
    private int status;
    private Set<CourseClass> classes;
}
