package org.example.coursemanager.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "username is mandatory")
    String username;
    @NotBlank(message = "password is mandatory")
    String password;
}
