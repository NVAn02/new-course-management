package org.example.coursemanager.service;

import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.payload.response.CourseClassDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassService {
    ResponseEntity<Object> getUserCourseClass(Long courseId, Long userId);
    ResponseEntity<List<CourseClassDTO> > getAllUserClasses(int userId);
    ResponseEntity<String> addUsersToClass( Long classId, List<Long> usersId);
    ResponseEntity<Object> getClass(Long id);
    ResponseEntity<String> addNewClass(CourseClassDTO newClassDTO, Long courseId);
    ResponseEntity<String> deleteStudentFromClass(Long classId, Long userId);
    ResponseEntity<String> updateClass(CourseClassDTO updateClassDTO);

    ResponseEntity<String> changeStatus(Long id);
}
