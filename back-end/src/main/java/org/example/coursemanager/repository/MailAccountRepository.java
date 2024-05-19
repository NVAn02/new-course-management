package org.example.coursemanager.repository;

import org.example.coursemanager.model.MailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAccountRepository extends JpaRepository<MailAccount, Long> {
}
