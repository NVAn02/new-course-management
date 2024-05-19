package org.example.coursemanager.service;

import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.payload.response.ScheduleDTO;
import org.example.coursemanager.repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final ModelMapper mapper;
    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ModelMapper mapper) {
        this.scheduleRepository = scheduleRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule schedule: scheduleList){
            scheduleDTOS.add(mapper.map(schedule, ScheduleDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleDTOS);
    }
}
