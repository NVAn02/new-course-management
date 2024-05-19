package org.example.coursemanager.model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.example.coursemanager.config.SendMailEvent;
import org.example.coursemanager.repository.MailTemplateRepository;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.NoSuchElementException;
import java.util.Set;

@Data
@Component
public class SendMailObject {

    private final MailTemplateRepository mailTemplateRepository;
    private final JavaMailSenderImpl emailSender;
    private final TemplateEngine templateEngine;
    @Async
    @EventListener
    public void sendEmail(SendMailEvent sendMailEvent){
        System.out.println("Start sending emails");
        Context context = new Context();
        Set<User> users = sendMailEvent.getUsers();
        CourseClass courseClass = sendMailEvent.getCourseClass();
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        for( User user : users){
            try {
                MailTemplate mailTemplateRegister = mailTemplateRepository.findById(5L).orElseThrow(() -> new NoSuchElementException("Template with ID 5 not found"));
                helper.setTo(user.getEmail());
                helper.setSubject(mailTemplateRegister.getSubject());


                context.setVariable("body", mailTemplateRegister.getBody()
                        .replace("\n", "<br>")
                        .replace("[user_name]", user.getName())
                        .replace("[start_day]", courseClass.getOpeningDay())
                        .replace("[day_one]", courseClass.getSchedule().getDayOne())
                        .replace("[day_one]", courseClass.getSchedule().getDayTwo())
                        .replace("[class_name]", courseClass.getName())
                        .replace("[course_name]", courseClass.getCourse().getName())
                        .replace("[teacher_name]", courseClass.getTeacher().getName())
                        .replace("[mail_send]", mailTemplateRegister.getMailAccount().getEmail() )
                        .replace("[phone]", "0865447197"));

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
        }
        System.out.println("Finish sending the email");
    }

}
