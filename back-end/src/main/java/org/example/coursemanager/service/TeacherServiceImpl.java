package org.example.coursemanager.service;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.Teacher;
import org.example.coursemanager.payload.response.CourseDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.ScheduleDTO;
import org.example.coursemanager.payload.response.TeacherDTO;
import org.example.coursemanager.repository.CourseClassRepository;
import org.example.coursemanager.repository.ScheduleRepository;
import org.example.coursemanager.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final ScheduleRepository scheduleRepository;

    private final CourseClassRepository courseClassRepository;
    private final ModelMapper mapper;
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ScheduleRepository scheduleRepository, CourseClassRepository courseClassRepository, ModelMapper mapper) {
        this.teacherRepository = teacherRepository;
        this.scheduleRepository = scheduleRepository;
        this.courseClassRepository = courseClassRepository;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<PageResponse> getAllTeachers(int pageNo, int pageSize) {
        if(pageNo <0 || pageSize <0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);
        if (teacherPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<TeacherDTO> teacherDTOS = teacherPage.getContent()
                .stream()
                .map(teacher -> mapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(teacherDTOS);
        pageResponse.setPageSize(teacherPage.getSize());
        pageResponse.setPageNo(teacherPage.getNumber());
        pageResponse.setTotalPages(teacherPage.getTotalPages());
        pageResponse.setTotalElements(teacherPage.getTotalElements());
        pageResponse.setLast(teacherPage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Override
    public ResponseEntity<Object> getTeacher(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            TeacherDTO teacherDTO = new TeacherDTO(teacher.getId(), teacher.getName(), teacher.getPhone(), teacher.getEmail(), teacher.getAddress(),teacher.getStatus(), teacher.getClasses());
            return ResponseEntity.status(HttpStatus.OK).body(teacherDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
    }

    @Override
    public ResponseEntity<String> addTeacher(Teacher newTeacher) {
        teacherRepository.save(newTeacher);
        return ResponseEntity.status(HttpStatus.OK).body("add new teacher successfully");
    }

    @Override
    public ResponseEntity<String> updateTeacher(Teacher updateTeacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(updateTeacher.getId());
        if (teacherOptional.isPresent()){
            Teacher oldTeacher = teacherOptional.get();
            oldTeacher.setName(updateTeacher.getName());
            oldTeacher.setAddress(updateTeacher.getAddress());
            oldTeacher.setPhone(updateTeacher.getPhone());
            oldTeacher.setEmail(updateTeacher.getEmail());
            teacherRepository.save(oldTeacher);
            return ResponseEntity.status(HttpStatus.OK).body("update teacher successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
    }

    @Override
    public ResponseEntity<String> changeStatusTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()){
            if(!teacher.get().getClasses().isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This teacher is still teaching");
            }
            if (teacher.get().getStatus() == 0){
                teacher.get().setStatus(1);

            }else {
                teacher.get().setStatus(0);

            }
            teacherRepository.save(teacher.get());
            return ResponseEntity.status(HttpStatus.OK).body("Change status successfully");

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher is not found");
    }

    @Override
    public ResponseEntity<List<TeacherDTO>> getTeachersBySchedule(Long scheduleId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if(scheduleOptional.isPresent()){
            Schedule schedule = scheduleOptional.get();

            List<Schedule> scheduleList = scheduleRepository.findAllByDayOneContainsAndDayTwoContains(Arrays.asList(schedule.getDayOne(),schedule.getDayTwo()));

            List<CourseClass> courseClasses = courseClassRepository.findAllByScheduleIn(scheduleList);
            Set<Teacher> teachers = new HashSet<>();
            for(CourseClass courseClass: courseClasses){
                teachers.add(courseClass.getTeacher());
            }
            List<Teacher>retTeachers =  teacherRepository.findAll();
            retTeachers.removeAll(teachers);
            retTeachers.removeIf(teacher -> teacher.getStatus() == 1);
            List<TeacherDTO> teacherDTOS = new ArrayList<>();
            for(Teacher teacher: retTeachers){
                teacherDTOS.add(mapper.map(teacher, TeacherDTO.class));
            }

            return ResponseEntity.status(HttpStatus.OK).body(teacherDTOS);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());

        }

    }
}
