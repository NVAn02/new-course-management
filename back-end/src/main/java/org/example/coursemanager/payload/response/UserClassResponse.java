package org.example.coursemanager.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.model.Score;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserClassResponse {
private CourseRegister courseRegister;
private Set<Score> scores;
}
