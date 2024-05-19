package org.example.coursemanager.repository;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.CourseRegister;
import org.example.coursemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRegisterRepository extends JpaRepository<CourseRegister, Long> {

    boolean existsByCourseAndUser(Course course, User user);
    List<CourseRegister> findAllByCourseAndUser(Course course, User user);

    List<CourseRegister> findAllByCourseIdAndScheduleIdAndStatus(Long courseId, Long scheduleId, int status);

    List<CourseRegister> findAllByCourseId(Long courseId);
    Set<CourseRegister> findAllByIdIn(List<Long> ids);

    @Query("Select cr from CourseRegister as cr where cr.user.id = :userId and (cr.schedule.dayOne in :days or cr.schedule.dayTwo in :days) ")
    List<CourseRegister> findAllByUserIdAndSchedule(Long userId, List<String> days);



}
