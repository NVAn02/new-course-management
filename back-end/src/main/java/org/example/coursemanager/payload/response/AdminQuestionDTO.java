package org.example.coursemanager.payload.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminQuestionDTO {
    private Long id;

    @NotBlank(message = "question is mandatory")
    private String question;

    @NotBlank(message = "option 1 is mandatory")
    private String option1;

    @NotBlank(message = "option 2 is mandatory")
    private String option2;

    @NotBlank(message = "option 3 is mandatory")
    private String option3;

    @NotBlank(message = "option 4 is mandatory")
    private String option4;

    @NotBlank(message = "correct answer is mandatory")
    private String correctAnswer;

    @NotBlank(message = " topic is mandatory")
    private String topic;
}
