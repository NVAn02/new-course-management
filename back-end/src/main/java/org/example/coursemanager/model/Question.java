package org.example.coursemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String correctAnswer;

    private String topic;

    @ManyToMany(mappedBy = "questions",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subject> subjects;
}
