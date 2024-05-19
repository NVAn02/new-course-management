package org.example.coursemanager.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.coursemanager.config.SendMailEvent;
import org.example.coursemanager.model.*;
import org.example.coursemanager.payload.response.CourseClassDTO;
import org.example.coursemanager.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;
import java.util.stream.Collectors;


@Service

public class ClassServiceImpl implements ClassService{

    private final CourseClassRepository courseClassRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final CourseRegisterRepository courseRegisterRepository;

    private final ScoreRepository scoreRepository;

    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    private final ApplicationEventPublisher applicationEventPublisher;


    private final TemplateEngine templateEngine;


    private final MailTemplateRepository mailTemplateRepository;

    @Autowired
    public ClassServiceImpl(CourseClassRepository courseClassRepository, CourseRepository courseRepository, UserRepository userRepository, CourseRegisterRepository courseRegisterRepository, ScoreRepository scoreRepository, TeacherRepository teacherRepository, ModelMapper mapper, ApplicationEventPublisher applicationEventPublisher, TemplateEngine templateEngine, MailTemplateRepository mailTemplateRepository) {
        this.courseClassRepository = courseClassRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseRegisterRepository = courseRegisterRepository;
        this.scoreRepository = scoreRepository;
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.templateEngine = templateEngine;
        this.mailTemplateRepository = mailTemplateRepository;
    }

    @Override
    public ResponseEntity<Object> getUserCourseClass(Long courseId, Long userId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Set<CourseClass> classes = optionalCourse.get().getClasses();

            Optional<CourseClass> retClassOptional = classes.stream()
                    .filter(courseClass -> courseClass.getStudents().stream().anyMatch(user -> user.getId().equals(userId)))
                    .findFirst();


            if(retClassOptional.isPresent()){
                CourseClassDTO retClass = mapper.map(retClassOptional.get(), CourseClassDTO.class);
                return ResponseEntity.status(HttpStatus.OK).body(retClass);

            }else
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("No Class Found");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

    }

    @Override
    public ResponseEntity<List<CourseClassDTO> > getAllUserClasses(int userId) {
        List<Course> courses = courseRepository.findCoursesByUserId((long) userId);
        List<CourseClass> classes = courses.stream()
                .flatMap(course -> course.getClasses().stream())
                .toList();

        List<CourseClass> userClasses = classes.stream()
                .filter(courseClass -> courseClass.getStudents().stream()
                        .anyMatch(student -> student.getId() == userId))
                .toList();

        List<CourseClassDTO> usCourseClassDTOS = new ArrayList<>();
        for(CourseClass courseClass : userClasses){
            CourseClassDTO classDTO = mapper.map(courseClass, CourseClassDTO.class);
            usCourseClassDTOS.add(classDTO);

        }

        return ResponseEntity.status(HttpStatus.OK).body(usCourseClassDTOS);
    }

    @Override
    public ResponseEntity<String> addUsersToClass(Long classId, List<Long> registersId) {
        Optional<CourseClass> courseClassOptional = courseClassRepository.findById(classId);
        if(courseClassOptional.isPresent()){
            CourseClass courseClass = courseClassOptional.get();
            if(courseClass.getStatus() == 1){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This class has been discontinued");
            }
            Set<CourseRegister> courseRegisters = courseRegisterRepository.findAllByIdIn(registersId);

            Set<User> users = courseRegisters.stream()
                    .map(CourseRegister::getUser)
                    .collect(Collectors.toSet());

            courseClass.getStudents().addAll(users);
            courseClass.setCourseRegisters(courseRegisters);
            for(CourseRegister courseRegister: courseRegisters){
                courseRegister.setCourseClass(courseClass);
                courseRegister.setStatus(1);
                courseRegisterRepository.save(courseRegister);
            }
            courseClassRepository.save(courseClass);

            //create student score
            Set<Subject> classSubjects = courseClassOptional.get().getCourse().getSubjects();

            for(User user:users){
                for(Subject subject:classSubjects){
                    scoreRepository.save(new Score(user, subject, classId));
                }

            }
            // sending mail
            applicationEventPublisher.publishEvent(new SendMailEvent(this, courseClass, users ));
            return ResponseEntity.status(HttpStatus.OK).body("add student to class successfully");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found");
        }

    }

    @Override
    public ResponseEntity<Object> getClass(Long id) {
        Optional<CourseClass> courseClass = courseClassRepository.findById(id);
        if(courseClass.isPresent()){
            CourseClassDTO classDTO = mapper.map(courseClass.get(), CourseClassDTO.class);

            return ResponseEntity.status(HttpStatus.OK).body(classDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("class not found");
        }

    }

    @Override
    public ResponseEntity<String> addNewClass(CourseClassDTO newClassDTO, Long courseId) {
        CourseClass newClass = mapper.map(newClassDTO, CourseClass.class);
        Course course = courseRepository.findById(courseId).orElseThrow( () -> new NoSuchElementException("course id not found"));
        newClass.setCourse(course);
        courseClassRepository.save(newClass);
        course.getClasses().add(newClass);
        courseRepository.save(course);
        Teacher teacher = teacherRepository.findById(newClass.getTeacher().getId()).orElseThrow(() -> new NoSuchElementException("teacher id not found"));
        teacher.getClasses().add(newClass);
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body("Created class successfully");
    }

    @Override
    public ResponseEntity<String> deleteStudentFromClass(Long classId, Long userId) {
        CourseClass courseClass = courseClassRepository.findById(classId).orElseThrow(() -> new NoSuchElementException("class id not found"));
        if (courseClass.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This class has been discontinued ");
        }
        Set<CourseRegister> courseRegisters = courseClass.getCourseRegisters();
        Optional<CourseRegister> optionalCourseRegister = courseRegisters.stream()
                .filter(register -> register.getUser().getId().equals(userId))
                .findFirst();
        if(optionalCourseRegister.isPresent()){
            CourseRegister courseRegister = optionalCourseRegister.get();
            courseRegister.setStatus(0);
            courseRegister.setCourseClass(null);
            courseRegisterRepository.save(courseRegister);
            courseClass.getCourseRegisters().remove(courseRegister);
        }


        courseClass.getStudents().removeIf(student -> Objects.equals(student.getId(), userId));

        courseClassRepository.save(courseClass);
        scoreRepository.deleteByClassIdAndUser( classId, userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user id not found")) );// xoá user khỏi bảng điểm

        return ResponseEntity.status(HttpStatus.OK).body("Successfully removed student from class");
    }

    @Override
    public ResponseEntity<String> updateClass(CourseClassDTO updateClass) {
        CourseClass courseClass = courseClassRepository.findById(updateClass.getId()).orElseThrow(() -> new NoSuchElementException("class id not found"));
        if(courseClass.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This class has been discontinued ");

        }
        Teacher oldTeacher = courseClass.getTeacher();
        oldTeacher.getClasses().remove(courseClass);
        teacherRepository.save(oldTeacher);
        courseClass.setName(updateClass.getName());
        courseClass.setOpeningDay(updateClass.getOpeningDay());
        courseClass.setTeacher(updateClass.getTeacher());
        Teacher newTeacher = teacherRepository.findById(updateClass.getTeacher().getId()).orElseThrow(() -> new NoSuchElementException("teacher id not found"));
        newTeacher.getClasses().add(courseClass);
        teacherRepository.save(newTeacher);
        courseClassRepository.save(courseClass);
        return ResponseEntity.status(HttpStatus.OK).body("Update class successfully");
    }

    @Override
    public ResponseEntity<String> changeStatus(Long id) {
        Optional<CourseClass> courseClassOptional = courseClassRepository.findById(id);
        if (courseClassOptional.isPresent()){
            CourseClass courseClass = courseClassOptional.get();
            courseClass.setStatus(courseClass.getStatus() == 1 ? 0 : 1);
            courseClassRepository.save(courseClass);
            return ResponseEntity.status(HttpStatus.OK).body("Change class status successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found");
        }
    }

    public void asyncMethod(Set<User> users, CourseClass courseClass) {

    }

}
