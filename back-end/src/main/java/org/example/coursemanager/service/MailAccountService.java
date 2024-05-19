package org.example.coursemanager.service;

import org.example.coursemanager.model.MailAccount;
import org.example.coursemanager.payload.response.MailAccountDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MailAccountService {
    ResponseEntity<String> addMailAccount(MailAccountDTO newMailAccountDTO);
    ResponseEntity<String> updateMailAccount(MailAccountDTO updateMailAccountDTO);
    ResponseEntity<List<MailAccountDTO>> getAllMailAccount();
    ResponseEntity<Object> getMailAccount( Long id);
    ResponseEntity<String> deleteAccount( Long id);
    ResponseEntity<String> testAccount( MailAccountDTO testAccountDTO);
}
