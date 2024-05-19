package org.example.coursemanager.service;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.Score;
import org.example.coursemanager.model.User;
import org.example.coursemanager.payload.response.CourseDTO;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.ScoreDTO;
import org.example.coursemanager.repository.CourseClassRepository;
import org.example.coursemanager.repository.ScoreRepository;
import org.example.coursemanager.repository.UserRepository;
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
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;

    private final UserRepository userRepository;
    private  final CourseClassRepository courseClassRepository;

    private final ModelMapper mapper;
    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository, UserRepository userRepository, CourseClassRepository courseClassRepository, ModelMapper mapper) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
        this.courseClassRepository = courseClassRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<PageResponse> getSubjectScore(Long classId, Long subjectId, int pageNo, int pageSize) {
        if(pageNo < 0 || pageSize <0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Score> scorePage = scoreRepository.findAllByClassIdAndSubjectId(pageable,classId, subjectId);
        if (scorePage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ScoreDTO> scoreDTOS = scorePage.getContent()
                .stream()
                .map(score -> mapper.map(score, ScoreDTO.class))
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(scoreDTOS);
        pageResponse.setPageSize(scorePage.getSize());
        pageResponse.setPageNo(scorePage.getNumber());
        pageResponse.setTotalPages(scorePage.getTotalPages());
        pageResponse.setTotalElements(scorePage.getTotalElements());
        pageResponse.setLast(scorePage.isLast());

        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
//        Set<Score> scoreSet = scoreRepository.findAllByClassIdAndSubjectId(classId, subjectId);
//        List<ScoreDTO> scoreDTOS = new ArrayList<>();
//        for (Score score: scoreSet){
//            scoreDTOS.add(mapper.map(score, ScoreDTO.class));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(scoreDTOS);
    }

    @Override
    public ResponseEntity<String> updateScore(ScoreDTO newScoreDTO) {
        CourseClass courseClass = courseClassRepository.findById(newScoreDTO.getClassId()).orElseThrow(() ->new NoSuchElementException("class id not found"));
        if(courseClass.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This class has been discontinued");
        }
        Score newScore  = mapper.map(newScoreDTO, Score.class);
        Optional<Score> optionalScore = scoreRepository.findById(newScore.getId());
        if (optionalScore.isPresent()){
            scoreRepository.save(newScore);
            return ResponseEntity.status(HttpStatus.OK).body("update score successfully");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Score not found");
        }
    }

    @Override
    public ResponseEntity<Object> getUserScore(Long userId, Long classId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            Set<Score> scores = scoreRepository.findAllByUserAndClassId(user, classId);
            List<ScoreDTO> scoreDTOS  = new ArrayList<>();
            for(Score score:scores){
                scoreDTOS.add(mapper.map(score, ScoreDTO.class));
            }
            return ResponseEntity.status(HttpStatus.OK).body(scoreDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }
}
