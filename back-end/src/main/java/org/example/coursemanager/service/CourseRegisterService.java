package org.example.coursemanager.service;

import org.example.coursemanager.payload.request.RegisterRequeset;
import org.example.coursemanager.payload.response.CourseRegisterDTO;
import org.example.coursemanager.payload.response.NumberRegisterResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseRegisterService {
    ResponseEntity<List<NumberRegisterResponse>> getNumberRegister();
    ResponseEntity<Object> registerCourse(RegisterRequeset registerRequeset);
    ResponseEntity<List<CourseRegisterDTO>> getAllCourseRegister(Long courseId, Long scheduleId);
    ResponseEntity<String> cancelRegistration(Long registerId, String token);

}
