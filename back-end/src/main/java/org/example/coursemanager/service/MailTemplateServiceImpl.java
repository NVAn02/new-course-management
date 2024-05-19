package org.example.coursemanager.service;

import org.example.coursemanager.model.MailTemplate;
import org.example.coursemanager.payload.response.MailTemplateDTO;
import org.example.coursemanager.repository.MailTemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;

    private final ModelMapper mapper;

    @Autowired
    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, ModelMapper mapper) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<String> addMailTemplate(MailTemplateDTO newMailTemplateDTO) {
        MailTemplate newMailTemplate = mapper.map(newMailTemplateDTO, MailTemplate.class);
        mailTemplateRepository.save(newMailTemplate);
        return ResponseEntity.status(HttpStatus.OK).body("add new mail template successfully");
    }

    @Override
    public ResponseEntity<String> updateMailTemplate(MailTemplateDTO updateMailTemplateDTO) {
        MailTemplate updateMailTemplate = mapper.map(updateMailTemplateDTO, MailTemplate.class);
        Optional<MailTemplate> mailTemplateOptional = mailTemplateRepository.findById(updateMailTemplate.getId());
        if (mailTemplateOptional.isPresent()) {
            mailTemplateRepository.save(updateMailTemplate);
            return ResponseEntity.status(HttpStatus.OK).body("update template successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail template not found");
        }
    }

    @Override
    public ResponseEntity<List<MailTemplateDTO>> getAllTemplate() {
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        List<MailTemplateDTO> mailTemplateDTOS = new ArrayList<>();
        for (MailTemplate mailTemplate : mailTemplateList) {
            mailTemplateDTOS.add(mapper.map(mailTemplate, MailTemplateDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mailTemplateDTOS);
    }

    @Override
    public ResponseEntity<Object> getTemplate(Long id) {
        Optional<MailTemplate> mailTemplateOptional = mailTemplateRepository.findById(id);
        if (mailTemplateOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.map(mailTemplateOptional.get(), MailTemplateDTO.class));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail template not found");
        }
    }
}
