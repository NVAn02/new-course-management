package org.example.coursemanager.service;

import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.payload.request.ChangePasswordRequest;
import org.example.coursemanager.payload.request.ResetPasswordRequest;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    ResponseEntity<Object> getUser(Long id, String token);
    ResponseEntity<PageResponse> getAllUsers(int pageNo, int pageSize);
    ResponseEntity<String> changeSatus(Long id);
    ResponseEntity<String> updateUser(Long id,UserDTO newUser);
    ResponseEntity<Set<CourseRegister>> getUserRegister(Long courseId, Long scheduleId, Long classId);

    ResponseEntity<String> verifyEmail(String email);

    ResponseEntity<String> resetPassword(ResetPasswordRequest resetPasswordRequest);

    ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest);
}
