package com.TimeForStudy.application.subject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link SubjectEntity}
 */
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
