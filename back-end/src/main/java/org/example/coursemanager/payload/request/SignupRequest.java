package org.example.coursemanager.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class SignupRequest {
    @NotBlank(message = "name is mandatory")
    String name;
    @NotBlank(message = "user name is mandatory")
    String username;
    @Email(message = "email is invalid")
    String email;
    @NotBlank(message = "password is mandatory")
    String password;
    Set<String> role;
}
