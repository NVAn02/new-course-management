package org.example.coursemanager.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.payload.response.CourseDTO;

import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.UserClassResponse;
import org.example.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    @Autowired
    public  CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addCourse(@RequestBody @Valid CourseDTO newCourseDTO) {
        return courseService.addCourse(newCourseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CourseDTO>> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/get-page")
    public ResponseEntity<PageResponse> getPageCourse(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize", defaultValue = "10") int pageSize
    ) {
        return courseService.getCoursePage(pageNo, pageSize);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get-my-course")
    public ResponseEntity<List<CourseDTO>> getMyCourse(@RequestHeader("Authorization") String token) {
        return courseService.getMyCourse(token);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get-my-course-detail")
    public ResponseEntity<List<UserClassResponse>> getMyCourseDetail(@RequestParam(name = "courseId") Long courseId, @RequestParam(name = "token") String token) {
        return courseService.getMyCourseRegisterDetail(courseId, token);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getCourseClasses/{courseId}")
    public ResponseEntity<Object> getAllCourseClass(@PathVariable Long courseId) {
        return courseService.getAllCourseClass(courseId);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-new-course")
    public ResponseEntity<String> addNewCourse(@RequestBody @Valid CourseDTO newCourseDTO) {
        return courseService.addNewCourse(newCourseDTO);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestBody @Valid CourseDTO updateCourseDTO) {
        return courseService.updateCourse(updateCourseDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

}
