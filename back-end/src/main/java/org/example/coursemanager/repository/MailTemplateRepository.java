package org.example.coursemanager.repository;

import org.example.coursemanager.model.MailAccount;
import org.example.coursemanager.model.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
    Optional<MailTemplate> findByName(String name);
    List<MailTemplate> findAllByMailAccount(MailAccount mailAccount);
}
