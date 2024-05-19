package org.example.coursemanager.repository;

import org.example.coursemanager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

@Query("SELECT s FROM Schedule s WHERE s.dayOne In :days  OR s.dayTwo in :days")
    List<Schedule> findAllByDayOneContainsAndDayTwoContains(List<String> days);
}
