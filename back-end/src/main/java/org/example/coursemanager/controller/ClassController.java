package org.example.coursemanager.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.example.coursemanager.payload.response.CourseClassDTO;
import org.example.coursemanager.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/class")
public class ClassController {
    private final ClassService classService;
    @Autowired
    public ClassController(ClassService classService){
        this.classService = classService;
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getClasses")
    public ResponseEntity<Object> getClasses(@RequestParam(name = "courseId") Long courseId, @RequestParam(name = "userId") Long userId) {
        return classService.getUserCourseClass(courseId, userId);

    }
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-user-classes/{userId}")
    public ResponseEntity<List<CourseClassDTO> > getAllUserClasses(@PathVariable int userId) {
        return classService.getAllUserClasses(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @PostMapping("/add-users/{classId}")
    public ResponseEntity<String> addUsersToClass(@PathVariable Long classId, @RequestBody List<Long> userIds){
        return classService.addUsersToClass(classId, userIds);
    }

//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getClass(@PathVariable Long id){
        return classService.getClass(id);

    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @PostMapping("/add/{courseId}")
    public ResponseEntity<String> addNewClass(@RequestBody @Valid CourseClassDTO newClassDTO, @PathVariable Long courseId){

        return classService.addNewClass(newClassDTO, courseId);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @DeleteMapping("/delete-student")
    public ResponseEntity<String> deleteStudentFromClass(@RequestParam(name="classId") Long classId,  @RequestParam(name="userId") Long userId){
        return classService.deleteStudentFromClass(classId, userId);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @PostMapping("/update")
    public ResponseEntity<String> updateClass(@RequestBody @Valid CourseClassDTO updateClassDTO){
        return classService.updateClass(updateClassDTO);
    }

    @Transactional
    @PutMapping("/change-status/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable Long id){
        return classService.changeStatus(id);
    }


}
