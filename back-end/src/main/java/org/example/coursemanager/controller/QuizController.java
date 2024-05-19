package org.example.coursemanager.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.coursemanager.model.Question;
import org.example.coursemanager.payload.response.AdminQuestionDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.example.coursemanager.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/question")
public class QuizController {
    private final QuestionService questionService;

    @Autowired
    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/get")
    public ResponseEntity<PageResponse> getAllQuestions(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        return questionService.getAllQuestions(pageNo, pageSize);
    }
    @GetMapping("/get-by-topic")
    public ResponseEntity<PageResponse> getAllQuestionsByTopic(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                               @RequestParam(name = "topic", defaultValue = "all") String topic){
        if(topic.equals("all")){
            return questionService.getAllQuestions(pageNo, pageSize);
        } else {
            return questionService.getAllQuestionsByTopic(pageNo, pageSize, topic);

        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<AdminQuestionDTO> getQuestion(@PathVariable Long id){
        return questionService.getQuestion(id);
    }
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(@RequestBody AdminQuestionDTO questionDTO){
        return questionService.updateQuestion(questionDTO);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        return questionService.deleteQuestion(id);
    }
    @Transactional
    @PostMapping("/add-new-question")
    public ResponseEntity<String> addNewQuestion(@RequestBody @Valid AdminQuestionDTO questionDTO){
        return questionService.addQuestion(questionDTO);
    }
    @Transactional
    @PostMapping("/submit-quiz")
    public ResponseEntity<String> submitQuiz(@RequestParam(name="subjectId") Long subjectId,
                                             @RequestParam(name="classId") Long classId,
                                             @RequestParam(name="userId") Long userId,
                                             @RequestBody List<QuestionDTO> questionDTOS){
//        System.out.println("Submit Quiz");
        return questionService.submitQuiz(subjectId, classId, userId,questionDTOS);
    }
}
