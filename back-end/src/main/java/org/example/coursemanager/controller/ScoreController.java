package org.example.coursemanager.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.example.coursemanager.payload.response.PageResponse;
import org.example.coursemanager.payload.response.ScoreDTO;

import org.example.coursemanager.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/score")

public class ScoreController {


    private final ScoreService scoreService;
    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-subject-score")
    public ResponseEntity<PageResponse>  getSubjectScore(@RequestParam(name="classId") Long classId,
                                                         @RequestParam(name="subjectId") Long subjectId,
                                                         @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
                                                         @RequestParam(name="pageSize", defaultValue = "10") int pageSize){
        return scoreService.getSubjectScore(classId, subjectId, pageNo, pageSize);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String> updateScore(@RequestBody @Valid ScoreDTO newScoreDTO){
        return scoreService.updateScore(newScoreDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get/{userId}/{classId}")
    public ResponseEntity<Object> getUserScore(@PathVariable Long userId, @PathVariable Long classId){
        return scoreService.getUserScore(userId, classId);
    }
}
