package org.example.coursemanager.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.example.coursemanager.model.Teacher;
import org.example.coursemanager.payload.response.PageResponse;

import org.example.coursemanager.payload.response.TeacherDTO;

import org.example.coursemanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teacher")

public class TeacherController {


    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<PageResponse> getAllTeachers(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize", defaultValue = "10") int pageSize
    ) {

        return teacherService.getAllTeachers(pageNo, pageSize);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getTeacher(@PathVariable Long id) {

        return teacherService.getTeacher(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody @Valid Teacher newTeacher) {
        return teacherService.addTeacher(newTeacher);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<String> updateTeacher(@RequestBody @Valid Teacher updateTeacher) {
        return teacherService.updateTeacher(updateTeacher);
    }


    @Transactional
    @PutMapping("/change-status/{id}")
    public ResponseEntity<String> changeStatusTeacher(@PathVariable Long id) {
        System.out.println("change status");
        return teacherService.changeStatusTeacher(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-teachers-by-schedule/{id}")
    public ResponseEntity<List<TeacherDTO>> getTeachersBySchedule(@PathVariable Long id){
        return teacherService.getTeachersBySchedule(id);
    }

}
