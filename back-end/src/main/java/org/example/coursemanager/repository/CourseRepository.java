package org.example.coursemanager.repository;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
    @Query("SELECT c FROM Course c JOIN CourseRegister cr ON c.id = cr.course.id WHERE cr.user.id = :userId")
    List<Course> findCoursesByUserId(@Param("userId") Long userId);

    Set<Course> findAllBySubjectsContains(Subject subject);
}
