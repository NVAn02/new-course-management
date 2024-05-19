package org.example.coursemanager.service;

import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.payload.response.CourseDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.UserClassResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity<String> addCourse(CourseDTO newCourseDTO);
    ResponseEntity<PageResponse> getCoursePage(int pageNo, int pageSize);
    ResponseEntity<List<CourseDTO>> getMyCourse(String token);
    ResponseEntity<List<UserClassResponse>> getMyCourseRegisterDetail(Long courseId, String token);
    ResponseEntity<Object> getAllCourseClass(Long courseId);
    ResponseEntity<String> addNewCourse(CourseDTO newCourseDTO);
    ResponseEntity<String> updateCourse(CourseDTO updateCourseDTO);
    ResponseEntity<Object> getCourse(Long id);

    ResponseEntity<List<CourseDTO>> getAllCourse();
}
