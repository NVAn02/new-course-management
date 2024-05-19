package org.example.coursemanager.payload.response;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.Subject;
import org.example.coursemanager.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private Long id;
    private String score;

    @NotNull(message = "user is mandatory")
    private User user;

    @NotNull(message = "subject is mandatory")
    private Subject subject;

    @NotNull(message = "class is mandatory")
    private Long classId;
}
