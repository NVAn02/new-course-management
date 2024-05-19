package org.example.coursemanager.service;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.Question;
import org.example.coursemanager.model.Subject;
import org.example.coursemanager.model.User;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.QuestionDTO;
import org.example.coursemanager.payload.response.SubjectDTO;
import org.example.coursemanager.payload.response.UserDTO;
import org.example.coursemanager.repository.CourseRepository;
import org.example.coursemanager.repository.QuestionRepository;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;
    private final QuestionRepository questionRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, CourseRepository courseRepository, ModelMapper mapper, QuestionRepository questionRepository) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public ResponseEntity<String> addSubject(Subject newSubject) {
        subjectRepository.save(newSubject);
        return ResponseEntity.status(HttpStatus.OK).body("add new subject successfully");
    }

    @Override
    public ResponseEntity<Object> getSubject(Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if(subjectOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(mapper.map(subjectOptional.get(), SubjectDTO.class));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        }
    }

    @Override
    public ResponseEntity<PageResponse> getPageSubject(int pageNo, int pageSize) {
        if (pageNo < 0 || pageSize <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);

        if (subjectPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<SubjectDTO> subjectDTOS = subjectPage.getContent()
                .stream()
                .map(subject -> mapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();

        pageResponse.setContent( subjectDTOS);
        pageResponse.setPageSize(subjectPage.getSize());
        pageResponse.setPageNo( subjectPage.getNumber());
        pageResponse.setTotalPages(subjectPage.getTotalPages());
        pageResponse.setTotalElements(subjectPage.getTotalElements());
        pageResponse.setLast(subjectPage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Override
    public ResponseEntity<String> updateSubject(Subject updateSubject) {
        subjectRepository.save(updateSubject);
        return ResponseEntity.status(HttpStatus.OK).body("update subject successfully");
    }

    @Override
    public ResponseEntity<String> deleteSubject(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if(optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            Set<Course> courseSet = courseRepository.findAllBySubjectsContains(subject);
            if(!courseSet.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This subject is still in use");
            }
            else{
                subjectRepository.delete(subject);

                return ResponseEntity.status(HttpStatus.OK).body("Deleted");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        }
    }

    @Override
    public ResponseEntity<List<SubjectDTO>> getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOS = subjects.stream().map(subject -> mapper.map(subject, SubjectDTO.class)).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(subjectDTOS);
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getListQuestion(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new NoSuchElementException("subject not found"));
        Set<Question> questions = subject.getQuestions();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question:questions){
            QuestionDTO questionDTO = mapper.map(question, QuestionDTO.class);

            questionDTOS.add(questionDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionDTOS);
    }

    @Override
    public ResponseEntity<String> addQuestions(Long id, List<Long> questionIds) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new NoSuchElementException("subject not found"));
        List<Question> questionList  = questionRepository.findAllByIdIn(questionIds);

        Set<Question> newQuestionList = subject.getQuestions();
        newQuestionList.addAll(questionList);
        subject.setQuestions(newQuestionList);
        subjectRepository.save(subject);
        return ResponseEntity.status(HttpStatus.OK).body("Add question subject successfully");
    }

}
