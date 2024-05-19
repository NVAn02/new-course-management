package org.example.coursemanager.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.example.coursemanager.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByIdIn(List<Long> ids);
    Page<Question> findAllByTopic( Pageable pageable, String topic);
}
