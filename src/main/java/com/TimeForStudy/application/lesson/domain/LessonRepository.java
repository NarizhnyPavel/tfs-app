package com.TimeForStudy.application.lesson.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link LessonEntity}
 */
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
