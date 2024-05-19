package org.example.coursemanager.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.coursemanager.payload.request.RegisterRequeset;
import org.example.coursemanager.payload.response.CourseRegisterDTO;
import org.example.coursemanager.payload.response.NumberRegisterResponse;
import org.example.coursemanager.service.CourseRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/register")
public class CourseRegisterController {

    private final CourseRegisterService courseRegisterService;

    @Autowired
    public CourseRegisterController(CourseRegisterService courseRegisterService) {
        this.courseRegisterService = courseRegisterService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-number-register")
    public ResponseEntity<List<NumberRegisterResponse>> getNumberRegister() {
        return courseRegisterService.getNumberRegister();

    }


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequeset registerRequeset) {
        return courseRegisterService.registerCourse(registerRequeset);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-course-register")
    public ResponseEntity<List<CourseRegisterDTO>> getAllCourseRegister(@RequestParam(name = "courseId") Long courseId, @RequestParam(name = "scheduleId") Long scheduleId) {
        return courseRegisterService.getAllCourseRegister(courseId, scheduleId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/delete-registration/{id}")
    public ResponseEntity<String> cancelRegistration(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return courseRegisterService.cancelRegistration(id, token);
    }
}


