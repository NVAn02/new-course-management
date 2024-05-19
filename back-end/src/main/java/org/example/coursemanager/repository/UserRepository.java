package org.example.coursemanager.repository;

import org.example.coursemanager.model.Course;
import org.example.coursemanager.model.CourseClass;
import org.example.coursemanager.model.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    Set<User> findAllByIdIn(List<Long> ids);

    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

}
