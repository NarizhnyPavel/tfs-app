package com.TimeForStudy.application.lessonposition.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link LessonPositionEntity}
 */
public interface LessonPositionRepository extends JpaRepository<LessonPositionEntity, Long> {

    List<LessonPositionEntity> findAllByPosition(Integer position);

    List<LessonPositionEntity> findAllByPositionAndDaysAndNumber(Integer position, Integer days, Integer number);

    List<LessonPositionEntity> findAllByPositionAndDays(Integer position, Integer days);
}
