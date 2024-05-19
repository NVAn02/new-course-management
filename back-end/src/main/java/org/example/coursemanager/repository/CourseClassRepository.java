package org.example.coursemanager.repository;

import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.Schedule;
import org.example.coursemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {


    List<CourseClass> findAllByScheduleIn(List<Schedule> schedules);

    @Query("SELECT c FROM CourseClass c WHERE :user MEMBER OF c.students")
    List<CourseClass> findAllByStudentsContainsUser(@Param("user")User user);






}
