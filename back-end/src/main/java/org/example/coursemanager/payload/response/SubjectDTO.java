package org.example.coursemanager.payload.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.Question;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    Long id;

    @NotBlank(message = "name is mandatory")
    String name;

    @NotBlank(message = "content is mandatory")
    String content;

    Set<AdminQuestionDTO> questions;
}
