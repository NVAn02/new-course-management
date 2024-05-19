package org.example.coursemanager.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@ToString

    @Entity
    public class Score {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String score;
        @ManyToOne(fetch = FetchType.EAGER)
        private User user;
        @ManyToOne(fetch = FetchType.EAGER)
        private Subject subject;
        private Long classId;

//    private int status;

    public Score(User user, Subject subject, Long classId) {
        this.user = user;
        this.subject = subject;
        this.classId = classId;
    }
}
