package org.example.coursemanager.controller;

import jakarta.validation.Valid;

import org.example.coursemanager.model.Question;
import org.example.coursemanager.model.Subject;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.example.coursemanager.payload.response.SubjectDTO;
import org.example.coursemanager.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subject")
public class SubjectController {


    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addSubject(@Valid @RequestBody Subject newSubject){
        return subjectService.addSubject(newSubject);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String> updateSubject(@Valid @RequestBody Subject updateSubject){
        return subjectService.updateSubject(updateSubject);
    }

    @GetMapping("/get")
    public ResponseEntity<List<SubjectDTO>> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping("/get-page")
    public ResponseEntity<PageResponse> getPageSubject(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize", defaultValue = "10") int pageSize
    ){
        return subjectService.getPageSubject(pageNo, pageSize);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getSubject(@PathVariable Long id){
        return subjectService.getSubject(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id){
        return subjectService.deleteSubject(id);
    }

            @GetMapping("/get-list-questions/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable Long id){
        return subjectService.getListQuestion(id);
    }
    @PostMapping("/add-questions/{id}")
    public ResponseEntity<String> addQuestions(@PathVariable Long id, @RequestBody List<Long> questionIds){
        return subjectService.addQuestions(id, questionIds);
    }

}
