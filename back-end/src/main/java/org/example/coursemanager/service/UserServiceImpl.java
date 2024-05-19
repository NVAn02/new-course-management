package org.example.coursemanager.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.coursemanager.model.*;
import org.example.coursemanager.payload.request.ChangePasswordRequest;
import org.example.coursemanager.payload.request.ResetPasswordRequest;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.UserDTO;
import org.example.coursemanager.payload.response.ResponseObject;
import org.example.coursemanager.repository.*;
import org.example.coursemanager.sercurity.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    private final CourseRegisterRepository courseRegisterRepository;


    private final JwtUtils jwtUtils;

    private final RoleRepository roleRepository;

    private final CourseClassRepository courseClassRepository;

    private final JavaMailSenderImpl emailSender;

    private final MailTemplateRepository mailTemplateRepository;

    private final TemplateEngine templateEngine;

    private final ForgotPasswordRepository forgotPasswordRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CourseRegisterRepository courseRegisterRepository, JwtUtils jwtUtils, RoleRepository roleRepository, CourseClassRepository courseClassRepository, JavaMailSenderImpl emailSender, MailTemplateRepository mailTemplateRepository, TemplateEngine templateEngine, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.courseRegisterRepository = courseRegisterRepository;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
        this.courseClassRepository = courseClassRepository;
        this.emailSender = emailSender;
        this.mailTemplateRepository = mailTemplateRepository;
        this.templateEngine = templateEngine;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Object> getUser(Long id, String token) {
        String myToken = token.split(" ")[1];
        if (!jwtUtils.validateJwtToken(myToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(" ", "Unauthorized", "false"));
        }
        String username = jwtUtils.getUserNameFromJwtToken(myToken);
        Optional<User> userOptional = userRepository.findByUsername(username);
                if(userOptional.isPresent()) {
                    User user = userOptional.get();
                    Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN).get();
                    if (user.getRoles().contains(roleAdmin)) {
                        Optional<User> retUserOptional = userRepository.findById(id);
                        if(retUserOptional.isPresent()){
                            return ResponseEntity.status(HttpStatus.OK).body(mapper.map(retUserOptional.get(), UserDTO.class));
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user id not found");
                        }

                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(user, UserDTO.class));
                    }
                }else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
                }

    }

    @Override
//    public ResponseEntity<List<UserDTO>> getAllUsers( int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        Page<User> userPage = userRepository.findAll(pageable);
//        List<User> users = userPage.getContent();
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for (User user: users){
//            userDTOS.add(mapper.map(user, UserDTO.class));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
//    }
    public ResponseEntity<PageResponse> getAllUsers(int pageNo, int pageSize) {
        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("name"));
        Page<User> userPage = userRepository.findAll(pageable);

        if (userPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<UserDTO> userDTOS = userPage.getContent()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();

        pageResponse.setContent( userDTOS);
        pageResponse.setPageSize(userPage.getSize());
        pageResponse.setPageNo( userPage.getNumber());
        pageResponse.setTotalPages(userPage.getTotalPages());
        pageResponse.setTotalElements(userPage.getTotalElements());
        pageResponse.setLast(userPage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Override
    public ResponseEntity<String> changeSatus(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<CourseClass> userClasses = courseClassRepository.findAllByStudentsContainsUser(user);
            List<CourseClass> userClassList = new ArrayList<>();
            if(!userClasses.isEmpty()){
                 userClassList = userClasses.stream().filter(userClass -> userClass.getStatus()== 0).toList();
            }
            if (userClasses.isEmpty() || userClassList.isEmpty()) {
                user.setStatus(user.getStatus() == 0 ? 1 : 0);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Change status successfully");
            } else {


                return ResponseEntity.status(HttpStatus.CONFLICT).body("Student still in class");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Override
    public ResponseEntity<String> updateUser(Long id, UserDTO newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAddress(newUser.getAddress());
            user.setName(newUser.getName());
            user.setPhone(newUser.getPhone());
            user.setBirthday(newUser.getBirthday());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("update user successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }

    @Override
    public ResponseEntity<Set<CourseRegister>> getUserRegister( Long courseId, Long scheduleId, Long classId) {
        CourseClass courseClass = courseClassRepository.findById(classId).orElseThrow(() -> new NoSuchElementException("class not found"));
        Set<User> students = courseClass.getStudents();

        List<CourseRegister> registers = courseRegisterRepository.findAllByCourseIdAndScheduleIdAndStatus(courseId, scheduleId, 0);
        Set<CourseRegister> courseRegisters = new HashSet<>(registers);
        courseRegisters.removeIf(register -> students.contains(register.getUser()));


        Map<User, CourseRegister> userToCourseRegisterMap = new HashMap<>();

        for (CourseRegister courseRegister : courseRegisters) {
            User user = courseRegister.getUser();
            // Nếu user chưa được thêm vào map hoặc user đã được thêm vào map nhưng courseRegister mới có id nhỏ hơn
            if (!userToCourseRegisterMap.containsKey(user) ||
                    userToCourseRegisterMap.get(user).getId() > courseRegister.getId()) {
                userToCourseRegisterMap.put(user, courseRegister);
            }
        }

// Lưu trữ lại các CourseRegister duy nhất trong Set
        Set<CourseRegister> uniqueCourseRegisters = new HashSet<>(userToCourseRegisterMap.values());


        return ResponseEntity.status(HttpStatus.OK).body(uniqueCourseRegisters);
    }

    @Override
    public ResponseEntity<String> verifyEmail(String email) {
        User user;
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
        }

        Integer otp = new Random().nextInt(900000) + 100000;

        Optional<ForgotPassword> forgotPasswordOptional = forgotPasswordRepository.findByUser(user);
        ForgotPassword forgotPassword;
        if (forgotPasswordOptional.isPresent()) {
            forgotPassword = forgotPasswordOptional.get();
            forgotPassword.setOtp(otp);
            forgotPassword.setExpirationDate(new Date(System.currentTimeMillis() + 120 * 1000));

        } else {
            forgotPassword = ForgotPassword.builder().otp(otp).user(user).expirationDate(new Date(System.currentTimeMillis() + 120 * 1000)).build();

        }


        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            MailTemplate mailTemplateRegister = mailTemplateRepository.findByName("verify email").get();
            helper.setTo(email);
            helper.setSubject(mailTemplateRegister.getSubject());
            Context context = new Context();
            context.setVariable("body", mailTemplateRegister.getBody().replace("[otp]", otp.toString()));
            String htmlContent = templateEngine.process("verify_email.html", context);
            helper.setText(htmlContent, true);

            emailSender.setUsername(mailTemplateRegister.getMailAccount().getEmail());
            emailSender.setPassword(mailTemplateRegister.getMailAccount().getPassword());
            emailSender.setHost(mailTemplateRegister.getMailAccount().getHost());
            emailSender.setPort(mailTemplateRegister.getMailAccount().getPort());
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        forgotPasswordRepository.save(forgotPassword);
        return ResponseEntity.status(HttpStatus.OK).body("Otp sended!");
    }

    @Override
    public ResponseEntity<String> resetPassword(ResetPasswordRequest changePasswordDTO) {

        Optional<User> userOptional = userRepository.findByEmail(changePasswordDTO.getEmail());
        ForgotPassword forgotPassword;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            forgotPassword = forgotPasswordRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Not found forgot password"));

            if (!Objects.equals(changePasswordDTO.getOtp(), forgotPassword.getOtp())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP is invalid");
            } else if (forgotPassword.getExpirationDate().before(new Date(System.currentTimeMillis()))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP has expired");
            } else {
                user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));

                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Change password successfully");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user with email " + changePasswordDTO.getEmail());
        }
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<User> userOptional = userRepository.findByEmail(changePasswordRequest.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(),user.getPassword())){
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Change password successfully");
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Current password does not match");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user with email " + changePasswordRequest.getEmail() );

        }
    }
}
