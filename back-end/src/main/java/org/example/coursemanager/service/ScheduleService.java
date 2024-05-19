package org.example.coursemanager.service;

import org.example.coursemanager.payload.response.ScheduleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {
    ResponseEntity<List<ScheduleDTO>> getAllSchedules();
}
