package org.example.coursemanager.service;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.coursemanager.model.*;
import org.example.coursemanager.payload.request.RegisterRequeset;
import org.example.coursemanager.payload.response.CourseRegisterDTO;
import org.example.coursemanager.payload.response.NumberRegisterResponse;
import org.example.coursemanager.payload.response.ResponseObject;
import org.example.coursemanager.repository.*;
import org.example.coursemanager.sercurity.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service

public class CourseRegisterServiceImpl implements CourseRegisterService {
    private final JwtUtils jwtUtils;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final CourseRegisterRepository courseRegisterRepository;

    private final CourseRepository courseRepository;

    private final PasswordEncoder encoder;

    private final JavaMailSenderImpl emailSender;


    private final TemplateEngine templateEngine;


    private final MailTemplateRepository mailTemplateRepository;

    private final ScheduleRepository scheduleRepository;


    private final ModelMapper mapper;

    @Autowired
    public CourseRegisterServiceImpl(JwtUtils jwtUtils, RoleRepository roleRepository, UserRepository userRepository, CourseRegisterRepository courseRegisterRepository, CourseRepository courseRepository, PasswordEncoder encoder, JavaMailSenderImpl emailSender, TemplateEngine templateEngine, MailTemplateRepository mailTemplateRepository, ScheduleRepository scheduleRepository, CourseClassRepository courseClassRepository, ModelMapper mapper) {
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.courseRegisterRepository = courseRegisterRepository;
        this.courseRepository = courseRepository;
        this.encoder = encoder;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.mailTemplateRepository = mailTemplateRepository;
        this.scheduleRepository = scheduleRepository;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<List<NumberRegisterResponse>> getNumberRegister() {


        List<NumberRegisterResponse> registerResponseList = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            for (Schedule schedule : course.getSchedules()) {
                List<CourseRegister> listRegisterCourse = courseRegisterRepository.findAllByCourseIdAndScheduleIdAndStatus(course.getId(), schedule.getId(), 0);
                if (!listRegisterCourse.isEmpty()) {
                    registerResponseList.add(new NumberRegisterResponse(course, schedule, listRegisterCourse.size()));

                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(registerResponseList);
    }

    @Override
    public ResponseEntity<Object> registerCourse(RegisterRequeset registerRequeset) {

        Optional<Course> courseOptional = courseRepository.findById(registerRequeset.getCourseId());
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(registerRequeset.getSchedule().getId());
        Schedule schedule;
        Course course;
        if (courseOptional.isPresent()) {
            course = courseOptional.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        if (scheduleOptional.isPresent()) {
            schedule = scheduleOptional.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found");
        }

        CourseRegister courseRegister = new CourseRegister();
        String messageUsername;
        String messagePassword;
        Context context = new Context();

        if (userRepository.existsByEmail(registerRequeset.getEmail())) {
            User user = userRepository.findByEmail(registerRequeset.getEmail()).orElseThrow(() -> new NoSuchElementException("user id not found"));
            List<CourseRegister> registerList = courseRegisterRepository.findAllByUserIdAndSchedule(user.getId(), Arrays.asList(registerRequeset.getSchedule().getDayOne(), registerRequeset.getSchedule().getDayTwo()));


            registerList.removeIf(register ->
                    register.getCourseClass() != null && register.getCourseClass().getStatus() == 1
            );


            if (!registerList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("", "Schedule conflicts with other courses", "fail"));
            }

            courseRegister.setUser(user);
            messageUsername = user.getUsername();
            messagePassword = null;

        } else {
            String name = registerRequeset.getName();
            String email = registerRequeset.getEmail();
            String phone = registerRequeset.getPhone();
            String address = registerRequeset.getAddress();

            String[] parts = name.split("\\s+");
            StringBuilder usernameBuilder = new StringBuilder();
            for (String part : parts) {
                usernameBuilder.append(part.toLowerCase().charAt(0));
            }


            Random random = new Random();
            int randomNumber = random.nextInt(9000) + 1000;
            String randomDigits = String.valueOf(randomNumber);
            usernameBuilder.append(randomDigits);

            String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder passwordBuilder = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                passwordBuilder.append(CHARACTERS.charAt(randomIndex));
            }
            String username = usernameBuilder.toString();
            messageUsername = username;
            String password = passwordBuilder.toString();
            messagePassword = password;
            String passwordEncode = encoder.encode(password);
            User newUser = new User(name, username, email, passwordEncode);
            newUser.setPhone(phone);
            newUser.setAddress(address);

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            newUser.setRoles(roles);
            userRepository.save(newUser);

            User registerUser = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("user not found with email " + email));
            courseRegister.setUser(registerUser);
        }

        courseRegister.setCourse(course);
        courseRegister.setSchedule(schedule);
        courseRegister.setStatus(0);

        List<CourseRegister> courseRegisters = courseRegisterRepository.findAllByCourseAndUser(courseRegister.getCourse(), courseRegister.getUser());

        courseRegisters.removeIf(register ->
                register.getCourseClass() != null && register.getCourseClass().getStatus() == 1
        );

        if (!courseRegisters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("", "You have registered for this course", "fail"));
        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            MailTemplate mailTemplateRegister = mailTemplateRepository.findByName("register").get();
            helper.setTo(registerRequeset.getEmail());
            helper.setSubject(mailTemplateRegister.getSubject());


            context.setVariable("body", mailTemplateRegister.getBody().replace("[course name]", courseRepository.findById(registerRequeset.getCourseId()).get().getName())

                    .replace("\n", "<br>")
                            .replace("[useremail]", registerRequeset.getEmail())
                    .replace("[name]", registerRequeset.getName())
                    .replace("[username]", messageUsername)
                    .replace("[password]", messagePassword == null ? "" : messagePassword)
                    .replace("password:", messagePassword == null ? "" : "password:")
                    .replace("[phone]", "0865447197")
                    .replace("[email]", mailTemplateRegister.getMailAccount().getEmail()));
            context.setVariable("image", "https://preview.colorlib.com/theme/onedu/assets/img/logo/logo.png.webp");

            String htmlContent = templateEngine.process("Registered_successfully.html", context);
            helper.setText(htmlContent, true);


            emailSender.setUsername(mailTemplateRegister.getMailAccount().getEmail());
            emailSender.setPassword(mailTemplateRegister.getMailAccount().getPassword());
            emailSender.setHost(mailTemplateRegister.getMailAccount().getHost());
            emailSender.setPort(mailTemplateRegister.getMailAccount().getPort());
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        courseRegisterRepository.save(courseRegister);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("", "registered successfully", "ok"));

    }

    @Override
    public ResponseEntity<List<CourseRegisterDTO>> getAllCourseRegister(Long courseId, Long scheduleId) {
        List<CourseRegister> courseRegisters = (courseRegisterRepository.findAllByCourseIdAndScheduleIdAndStatus(courseId, scheduleId, 0));
        List<CourseRegisterDTO> courseRegisterDTOS = new ArrayList<>();
        for (CourseRegister courseRegister : courseRegisters) {
            courseRegisterDTOS.add(mapper.map(courseRegister, CourseRegisterDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseRegisterDTOS);
    }

    @Override
    public ResponseEntity<String> cancelRegistration(Long registerId, String token) {
        String myToken = token.split(" ")[1];
        System.out.println("myToken: " + myToken);
        CourseRegister courseRegister = courseRegisterRepository.findById(registerId).orElseThrow(null);
        if(courseRegister == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course register not found");
        }
        if(courseRegister.getCourseClass() != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You have been added to the class");
        }
        if (!jwtUtils.validateJwtToken(myToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        String username = jwtUtils.getUserNameFromJwtToken(myToken);

        if(courseRegister.getUser().getUsername().equals(username)){
            System.out.println("delete register");
            courseRegisterRepository.delete(courseRegister);
        }
        return ResponseEntity.status(HttpStatus.OK).body("canceled course registration");
    }

}
