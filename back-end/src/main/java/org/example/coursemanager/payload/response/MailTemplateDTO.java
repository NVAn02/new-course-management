package org.example.coursemanager.payload.response;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.MailAccount;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailTemplateDTO {
    Long id;

    MailAccount mailAccount;

    @NotBlank(message = "name is mandatory")
    String name;

    @NotBlank(message = "subject is mandatory")
    String subject;

    @NotBlank(message = "body is mandatory")
    String body;
}
