package org.example.coursemanager.service;

import org.example.coursemanager.model.MailTemplate;
import org.example.coursemanager.payload.response.MailTemplateDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MailTemplateService {
    ResponseEntity<String> addMailTemplate(MailTemplateDTO newMailTemplateDTO);
    ResponseEntity<String> updateMailTemplate(MailTemplateDTO updateMailTemplateDTO);
    ResponseEntity<List<MailTemplateDTO>> getAllTemplate();
    ResponseEntity<Object> getTemplate(Long id);
}
