package com.TimeForStudy.application.semester.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link SemesterEntity}
 */
public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {
}
