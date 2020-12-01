package com.TimeForStudy.application.lesson.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий доступа к сущности {@link LessonEntity}
 */
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    /**
     * Получение учебного занятия по идентификатору
     *
     * @param id идентификатор группы.
     * @return учебное занятие.
     */
    @Query ("from LessonEntity where id = :id")
    LessonEntity getById(@Param("id") Long id);
}
