package com.TimeForStudy.application.lessonposition.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link LessonPositionEntity}
 */
public interface LessonPositionRepository extends JpaRepository<LessonPositionEntity, Long> {
}
