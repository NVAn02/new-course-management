package org.example.coursemanager.service;

import org.example.coursemanager.model.*;
import org.example.coursemanager.payload.response.*;
import org.example.coursemanager.repository.*;
import org.example.coursemanager.sercurity.jwt.JwtUtils;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseRegisterRepository courseRegisterRepository;

    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    private final ModelMapper mapper;
    private final JwtUtils jwtUtils;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseRegisterRepository courseRegisterRepository, CourseClassRepository courseClassRepository, SubjectRepository subjectRepository, UserRepository userRepository, ScoreRepository scoreRepository, ModelMapper mapper, JwtUtils jwtUtils) {
        this.courseRepository = courseRepository;
        this.courseRegisterRepository = courseRegisterRepository;

        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
        this.mapper = mapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<String> addCourse(CourseDTO newCourseDTO) {
        Course newCourse = mapper.map(newCourseDTO, Course.class);
        courseRepository.save(newCourse);
        return ResponseEntity.status(HttpStatus.OK).body("create new course successfully");

    }

    @Override
    public ResponseEntity<PageResponse> getCoursePage(int pageNo, int pageSize) {
        if (pageNo < 0 || pageSize < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Course> coursePage = courseRepository.findAll(pageable);
        if (coursePage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<CourseDTO> courseDTOS = coursePage.getContent()
                .stream()
                .map(course -> mapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(courseDTOS);
        pageResponse.setPageSize(coursePage.getSize());
        pageResponse.setPageNo(coursePage.getNumber());
        pageResponse.setTotalPages(coursePage.getTotalPages());
        pageResponse.setTotalElements(coursePage.getTotalElements());
        pageResponse.setLast(coursePage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Override
    public ResponseEntity<List<CourseDTO>> getMyCourse(String token) {
        String myToken = token.split(" ")[1];
        String username = jwtUtils.getUserNameFromJwtToken(myToken);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            List<Course> courses = courseRepository.findCoursesByUserId(userOptional.get().getId());
            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course course : courses) {
                courseDTOS.add(mapper.map(course, CourseDTO.class));
            }
            return ResponseEntity.status(HttpStatus.OK).body(courseDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<UserClassResponse>> getMyCourseRegisterDetail(Long courseId, String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("user not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("course not found"));
        List<CourseRegister> courseRegisters = courseRegisterRepository.findAllByCourseAndUser(course, user);
        List<UserClassResponse> responses = new ArrayList<>();
        for (CourseRegister courseRegister: courseRegisters){
            UserClassResponse userClassResponse = new UserClassResponse();
            if (courseRegister.getCourseClass() != null){
                Set<Score> scores = scoreRepository.findAllByUserAndClassId(user, courseRegister.getCourseClass().getId());
                userClassResponse.setScores(scores);
            }
            userClassResponse.setCourseRegister(courseRegister);
            responses.add(userClassResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    @Override
    public ResponseEntity<Object> getAllCourseClass(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Set<CourseClass> classes = courseOptional.get().getClasses();
            List<CourseClassDTO> courseClassDTOS = new ArrayList<>();
            for (CourseClass courseClass : classes) {
                CourseClassDTO classDTO = mapper.map(courseClass, CourseClassDTO.class);
                courseClassDTOS.add(classDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(courseClassDTOS);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    @Override
    public ResponseEntity<String> addNewCourse(CourseDTO newCourseDTO) {
        Course newCourse = mapper.map(newCourseDTO, Course.class);
        courseRepository.save(newCourse);
        return ResponseEntity.status(HttpStatus.OK).body("add new class successfully");
    }

    @Override
    public ResponseEntity<String> updateCourse(CourseDTO updateCourseDTO) {
        Course updateCourse = mapper.map(updateCourseDTO, Course.class);
        courseRepository.save(updateCourse);
        return ResponseEntity.status(HttpStatus.OK).body("update course successfully");
    }

    @Override
    public ResponseEntity<Object> getCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            CourseDTO courseDTO = mapper.map(course, CourseDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course not found");
        }

    }

    @Override
    public ResponseEntity<List<CourseDTO>> getAllCourse() {

        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = courses.stream()
                .map(course -> mapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOS);

    }
}
