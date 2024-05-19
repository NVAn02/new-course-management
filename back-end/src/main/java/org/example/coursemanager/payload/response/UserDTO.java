package org.example.coursemanager.payload.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.ForgotPassword;
import org.example.coursemanager.model.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;

    @NotNull(message = "user name is mandatory")
    @Size(max = 50)
    private String name;

    private String phone;

    private String address;

    private String birthday;

    @NotNull(message = "username is mandatory")
    @Size(max = 20)
    private String username;

    @NotNull(message = "user email is mandatory")
    @Size(max = 50)
    @Email
    private String email;

    private int status;
    private Set<Role> roles = new HashSet<>();
}
