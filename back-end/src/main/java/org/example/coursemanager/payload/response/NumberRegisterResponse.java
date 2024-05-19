package org.example.coursemanager.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.Schedule;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NumberRegisterResponse {
    private Course course;
    private Schedule schedule;
    private int numRegister;
}
