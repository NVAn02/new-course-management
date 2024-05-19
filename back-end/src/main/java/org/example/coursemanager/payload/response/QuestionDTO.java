package org.example.coursemanager.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO {
    private Long id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String topic;

    private String selectedAnswer;
}
