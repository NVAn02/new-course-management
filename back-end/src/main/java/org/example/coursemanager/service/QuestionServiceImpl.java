package org.example.coursemanager.service;

import org.example.coursemanager.model.*;
import org.example.coursemanager.payload.response.AdminQuestionDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.example.coursemanager.payload.response.UserDTO;
import org.example.coursemanager.repository.CourseClassRepository;
import org.example.coursemanager.repository.QuestionRepository;
import org.example.coursemanager.repository.ScoreRepository;
import org.example.coursemanager.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{
    private  final SubjectRepository subjectRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper mapper;
    private final ScoreRepository scoreRepository;
    private final CourseClassRepository courseClassRepository;

    @Autowired
    public QuestionServiceImpl(SubjectRepository subjectRepository, QuestionRepository questionRepository, ModelMapper mapper, ScoreRepository scoreRepository, CourseClassRepository courseClassRepository) {
        this.subjectRepository = subjectRepository;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
        this.scoreRepository = scoreRepository;
        this.courseClassRepository = courseClassRepository;
    }

    @Override
    public ResponseEntity<PageResponse> getAllQuestions(int pageNo, int pageSize) {
        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

//        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Question> questionPage = questionRepository.findAll(pageable);

        if (questionPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<AdminQuestionDTO> questionDTOS = questionPage.getContent()
                .stream()
                .map(question -> mapper.map(question, AdminQuestionDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();

        pageResponse.setContent( questionDTOS);
        pageResponse.setPageSize(questionPage.getSize());
        pageResponse.setPageNo( questionPage.getNumber());
        pageResponse.setTotalPages(questionPage.getTotalPages());
        pageResponse.setTotalElements(questionPage.getTotalElements());
        pageResponse.setLast(questionPage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Override
    public ResponseEntity<AdminQuestionDTO> getQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("question not found"));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(question, AdminQuestionDTO.class));
    }

    @Override
    public ResponseEntity<String> addQuestion(AdminQuestionDTO questionDTO) {


        Question question = mapper.map(questionDTO, Question.class);
        System.out.println(question.toString());
        questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.OK).body("Add new question successfully");
    }

    @Override
    public ResponseEntity<String> updateQuestion(AdminQuestionDTO questionDTO) {
        Question question = mapper.map(questionDTO, Question.class);
//        question.setCorrectAnswer(questionDTO.getSelectedAnswer());
        questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.OK).body("Update question successfully");
    }

    @Override
    public ResponseEntity<String> deleteQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow( () -> new NoSuchElementException("question not found"));
        if (!question.getSubjects().isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This question still in use");
        }
        questionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted question successfully " );
    }

    @Override
    public ResponseEntity<String> submitQuiz(Long subjectId,
                                             Long classId,
                                             Long userId,
                                             List<QuestionDTO> questionDTOS) {
        Score subjectScore = scoreRepository.findBySubjectIdAndClassIdAndUserId(subjectId, classId, userId);
        CourseClass courseClass = courseClassRepository.findById(classId).orElseThrow(() -> new NoSuchElementException("class not found"));
        if(courseClass.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This class has been discontinued");
        }
        if(subjectScore.getScore() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You have completed this test");
        }


        int correctAnswers = 0;
        Subject subject = subjectRepository.findById(subjectId).orElseThrow( () -> new NoSuchElementException("Subject not found"));
        for(QuestionDTO questionDTO: questionDTOS){
            Question question = questionRepository.findById(questionDTO.getId()).orElseThrow(() -> new NoSuchElementException("question not found"));
            if(questionDTO.getSelectedAnswer() != null && questionDTO.getSelectedAnswer().trim().equals(question.getCorrectAnswer().trim())){
                correctAnswers ++;
            }else{
                System.out.println(questionDTO.toString());
            }
        }
        int totalQues = subject.getQuestions().size();
        double percentage = (double) correctAnswers / totalQues;
        int finalScore = (int) Math.round(percentage * 100);
        subjectScore.setScore(finalScore+"");
        scoreRepository.save(subjectScore);

        return ResponseEntity.status(HttpStatus.OK).body("Correct answer: " + correctAnswers +"/" + totalQues + " \n Total score: " + finalScore);
    }

    @Override
    public ResponseEntity<PageResponse> getAllQuestionsByTopic(int pageNo, int pageSize, String topic) {
        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Question> questionPage = questionRepository.findAllByTopic(pageable, topic);
        List<AdminQuestionDTO> questionDTOS = questionPage.getContent()
                .stream()
                .map(question -> mapper.map(question, AdminQuestionDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();

        pageResponse.setContent( questionDTOS);
        pageResponse.setPageSize(questionPage.getSize());
        pageResponse.setPageNo( questionPage.getNumber());
        pageResponse.setTotalPages(questionPage.getTotalPages());
        pageResponse.setTotalElements(questionPage.getTotalElements());
        pageResponse.setLast(questionPage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }
}
