package com.TimeForStudy.application.positioncancel.domain;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link PositionCancelEntity}
 */
public interface PositionCancelRepository extends JpaRepository<PositionCancelEntity, Long> {

    /**
     * Получение списка отменённых занятий по номеру недели.
     *
     * @param number номер недели.
     * @return список отменённых занятий.
     */
    List<PositionCancelEntity> findAllByCancelWeek(Integer number);

    /**
     * Получение списка отменённых занятий по позиции занятия.
     *
     * @param lessonPositionEntity позиции занятия.
     * @return список отменённых занятий.
     */
    List<PositionCancelEntity> findAllByLessonPositionEntity(LessonPositionEntity lessonPositionEntity);
}
