package org.example.coursemanager.service;

import jakarta.mail.MessagingException;
import org.example.coursemanager.model.MailAccount;
import org.example.coursemanager.model.MailTemplate;
import org.example.coursemanager.payload.response.MailAccountDTO;
import org.example.coursemanager.repository.MailAccountRepository;
import org.example.coursemanager.repository.MailTemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MailAccountServiceImpl implements MailAccountService {

    private final MailTemplateRepository mailTemplateRepository;

    private final MailAccountRepository mailAccountRepository;

    private final JavaMailSenderImpl emailSender;

    private final ModelMapper mapper;

    @Autowired
    public MailAccountServiceImpl(MailTemplateRepository mailTemplateRepository, MailAccountRepository mailAccountRepository, JavaMailSenderImpl emailSender, ModelMapper mapper) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailAccountRepository = mailAccountRepository;
        this.emailSender = emailSender;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<String> addMailAccount(MailAccountDTO newMailAccountDTO) {
        MailAccount newMailAccount = mapper.map(newMailAccountDTO, MailAccount.class);
        mailAccountRepository.save(newMailAccount);
        return ResponseEntity.status(HttpStatus.OK).body("add new mail account successfully");

    }

    @Override
    public ResponseEntity<String> updateMailAccount(MailAccountDTO updateMailAccountDTO) {
        MailAccount updateMailAccount = mapper.map(updateMailAccountDTO, MailAccount.class);
        mailAccountRepository.save(updateMailAccount);
        return ResponseEntity.status(HttpStatus.OK).body("update mail account successfully");

    }

    @Override
    public ResponseEntity<List<MailAccountDTO>> getAllMailAccount() {
        List<MailAccount> mailAccounts = mailAccountRepository.findAll();
        List<MailAccountDTO> mailAccountDTOS = new ArrayList<>();
        for (MailAccount mailAccount : mailAccounts) {

            mailAccountDTOS.add(mapper.map(mailAccount, MailAccountDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mailAccountDTOS);
    }

    @Override
    public ResponseEntity<Object> getMailAccount(Long id) {
        Optional<MailAccount> mailAccountOptional = mailAccountRepository.findById(id);
        if (mailAccountOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.map(mailAccountOptional.get(), MailAccountDTO.class));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mail account not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteAccount(Long id) {
        Optional<MailAccount> mailAccountOptional = mailAccountRepository.findById(id);
        if (mailAccountOptional.isPresent()) {
            MailAccount mailAccount = mailAccountOptional.get();
            List<MailTemplate> mailTemplateList = mailTemplateRepository.findAllByMailAccount(mailAccount);
            if (mailTemplateList.isEmpty()) {
                mailAccountRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This account is still in use");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail account not found");
        }

    }

    @Override
    public ResponseEntity<String> testAccount(MailAccountDTO testAccountDTO) {
        MailAccount testAccount = mapper.map(testAccountDTO, MailAccount.class);
        emailSender.setUsername(testAccount.getEmail());
        emailSender.setPassword(testAccount.getPassword());
        emailSender.setPort(testAccount.getPort());
        emailSender.setHost(testAccount.getHost());
        try {
            emailSender.testConnection();
            return ResponseEntity.status(HttpStatus.OK).body("Successful connection");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Connection failed");
        }
    }
}
