package org.example.coursemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class MailAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
