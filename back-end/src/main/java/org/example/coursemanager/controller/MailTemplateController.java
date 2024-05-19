package org.example.coursemanager.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.example.coursemanager.payload.response.MailTemplateDTO;

import org.example.coursemanager.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mail")
public class MailTemplateController {

    private final MailTemplateService mailTemplateService;

    @Autowired
    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addMailTemplate(@RequestBody @Valid MailTemplateDTO newMailTemplateDTO){
        return mailTemplateService.addMailTemplate(newMailTemplateDTO);

    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<String> updateMailTemplate(@RequestBody @Valid MailTemplateDTO updateMailTemplateDTO){
        return mailTemplateService.updateMailTemplate(updateMailTemplateDTO);
    }
    @GetMapping("/get")
    public  ResponseEntity<List<MailTemplateDTO>> getAllTemplate(){
        return mailTemplateService.getAllTemplate();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getTemplate(@PathVariable Long id){
        return mailTemplateService.getTemplate(id);
    }

}
