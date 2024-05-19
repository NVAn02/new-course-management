package org.example.coursemanager.service;

import org.example.coursemanager.model.Score;
import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.ScoreDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScoreService {
//    ResponseEntity<List<ScoreDTO>> getSubjectScore(Long classId, Long subjectId);
    ResponseEntity<PageResponse> getSubjectScore(Long classId, Long subjectId, int pageNo, int pageSize);
    ResponseEntity<String> updateScore(ScoreDTO newScoreDTO);
    ResponseEntity<Object> getUserScore( Long userId,Long classId);

}
