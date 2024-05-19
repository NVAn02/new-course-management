package org.example.coursemanager.repository;

import org.example.coursemanager.model.ForgotPassword;
import org.example.coursemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
//    void deleteByUser(User user);

    Optional<ForgotPassword> findByUser(User user);
}
