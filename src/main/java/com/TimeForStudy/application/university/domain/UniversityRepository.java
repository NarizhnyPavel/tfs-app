package com.TimeForStudy.application.university.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link UniversityEntity}
 */
public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {

}
