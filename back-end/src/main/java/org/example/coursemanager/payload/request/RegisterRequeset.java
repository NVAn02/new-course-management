package org.example.coursemanager.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.coursemanager.model.Schedule;


@Data
public class RegisterRequeset {
    @NotBlank(message = "name is mandatory")
    String name;
    @NotBlank(message = "email is mandatory")
    String email;
    @NotBlank(message = "phone number is mandatory")
    @Pattern(regexp = "^0[0-9]{9}$", message = "phone number is invalid")
    String phone;
    String address;
    Long courseId;
    Schedule schedule;

}
