package org.example.coursemanager.repository;


import org.example.coursemanager.model.Score;
import org.example.coursemanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Page<Score> findAllByClassIdAndSubjectId(Pageable pageable, Long classId, Long subjectId);

    void deleteByClassIdAndUser(Long classId, User user);

    Set<Score> findAllByUserAndClassId(User user, Long classId);

    Score findBySubjectIdAndClassIdAndUserId(Long subjectId, Long classId, Long userId);
}
