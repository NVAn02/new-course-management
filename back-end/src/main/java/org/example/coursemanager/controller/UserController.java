package org.example.coursemanager.controller;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.payload.request.ChangePasswordRequest;
import org.example.coursemanager.payload.request.ResetPasswordRequest;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.UserDTO;

import org.example.coursemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return userService.getUser(id, token);

    }


    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<PageResponse> getAllUser(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getAllUsers(pageNo, pageSize);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @PutMapping("/change-status/{id}")
    public ResponseEntity<String> changStatus(@PathVariable Long id) {

        return userService.changeSatus(id);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam(name = "id") Long id, @Valid @RequestBody UserDTO newUser) {

        return userService.updateUser(id, newUser);
    }

    @GetMapping("/get-user-register")
    public ResponseEntity<Set<CourseRegister>> getUserRegister(@RequestParam(name = "courseId") Long courseId,
                                                               @RequestParam(name = "scheduleId") Long scheduleId,
                                                               @RequestParam(name = "classId") Long classId) {
        return userService.getUserRegister(courseId, scheduleId, classId);
    }

    @Transactional
    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable @NotBlank String email) {
        return userService.verifyEmail(email);
    }

    @Transactional
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return userService.resetPassword(resetPasswordRequest);
    }

    @Transactional
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest);
    }
}
