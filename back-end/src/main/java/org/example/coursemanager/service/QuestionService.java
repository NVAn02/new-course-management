package org.example.coursemanager.service;

import org.example.coursemanager.model.Question;
import org.example.coursemanager.payload.response.AdminQuestionDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<PageResponse> getAllQuestions(int pageNo, int pageSize);
    ResponseEntity<AdminQuestionDTO> getQuestion(Long id);
    ResponseEntity<String> addQuestion(AdminQuestionDTO questionDTO);
    ResponseEntity<String> updateQuestion(AdminQuestionDTO questionDTO);
    ResponseEntity<String> deleteQuestion(Long id);

    ResponseEntity<String> submitQuiz(Long subjectId,
                                      Long classId,
                                        Long userId
            ,List<QuestionDTO> questionDTOS);

    ResponseEntity<PageResponse> getAllQuestionsByTopic(int pageNo, int pageSize, String topic);
}
