package org.example.coursemanager.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.example.coursemanager.payload.response.MailAccountDTO;

import org.example.coursemanager.service.MailAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mail-account")
public class MailAccountController {

    private final MailAccountService mailAccountService;

    @Autowired
    public MailAccountController(MailAccountService mailAccountService) {
        this.mailAccountService = mailAccountService;
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addMailAccount(@RequestBody @Valid MailAccountDTO newMailAccountDTO) {
        return mailAccountService.addMailAccount(newMailAccountDTO);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<String> updateMailAccount(@RequestBody @Valid MailAccountDTO updateMailAccountDTO) {
        return mailAccountService.updateMailAccount(updateMailAccountDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<MailAccountDTO>> getAllMailAccount() {
        return mailAccountService.getAllMailAccount();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMailAccount(@PathVariable Long id) {
        return mailAccountService.getMailAccount(id);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return mailAccountService.deleteAccount(id);
    }

    @PostMapping("/test-account")
    public ResponseEntity<String> testAccount(@RequestBody @Valid MailAccountDTO testAccountDTO) {
        return mailAccountService.testAccount(testAccountDTO);

    }

}
