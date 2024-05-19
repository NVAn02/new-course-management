package org.example.coursemanager.payload.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailAccountDTO {
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "host is mandatory")
    private String host;
    @Positive(message = "port is invalid")
    private int port;
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
