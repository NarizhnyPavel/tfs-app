package com.TimeForStudy.application.lessontype.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link LessonTypeEntity}
 */
public interface LessonTypeRepository extends JpaRepository<LessonTypeEntity, Long> {
}
