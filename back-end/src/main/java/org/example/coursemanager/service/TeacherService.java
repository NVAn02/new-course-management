package org.example.coursemanager.service;

import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.Teacher;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.ScheduleDTO;
import org.example.coursemanager.payload.response.TeacherDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    ResponseEntity<PageResponse> getAllTeachers(int pageNo, int pageSize);
    ResponseEntity<Object> getTeacher(Long id);
    ResponseEntity<String> addTeacher(Teacher newTeacher);
    ResponseEntity<String> updateTeacher(Teacher updateTeacher);
    ResponseEntity<String> changeStatusTeacher(Long id);
    ResponseEntity<List<TeacherDTO>> getTeachersBySchedule(Long scheduleId);
}
