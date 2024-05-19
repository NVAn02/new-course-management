package org.example.coursemanager.service;

import org.example.coursemanager.model.Question;
import org.example.coursemanager.model.Subject;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.example.coursemanager.payload.response.SubjectDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    ResponseEntity<String> addSubject(Subject newSubject);
    ResponseEntity<Object> getSubject(Long id);
    ResponseEntity<PageResponse> getPageSubject(int pageNo,int pageSize);
    ResponseEntity<String> updateSubject(Subject updateSubject);
    ResponseEntity<String> deleteSubject(Long id);

    ResponseEntity<List<SubjectDTO>> getAllSubject();

    ResponseEntity<List<QuestionDTO>> getListQuestion(Long id);

    ResponseEntity<String> addQuestions(Long id, List<Long> questionIds);
}
