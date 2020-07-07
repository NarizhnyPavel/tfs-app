package com.TimeForStudy.application.lesson.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий доступа к сущности {@link LessonEntity}
 */
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    @Query ("from LessonEntity where id = :id")
    LessonEntity getById(@Param("id") Long id);
}
