package org.example.coursemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class MailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    MailAccount mailAccount;
    @NotBlank(message = "name is mandatory")
    String name;
    @NotBlank(message = "subject is mandatory")
    String subject;
    @NotBlank(message = "body is madatory")
    String body;
}
