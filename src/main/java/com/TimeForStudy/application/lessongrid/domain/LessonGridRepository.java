package com.TimeForStudy.application.lessongrid.domain;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link LessonGridEntity}
 */
public interface LessonGridRepository extends JpaRepository<LessonGridEntity, Long> {

    /**
     * Получение списка позиций сетки расписания для учебного заведения.
     *
     * @param universityEntity учебное заведение.
     * @return список позиций сетки расписания.
     */
    List<LessonGridEntity> findAllByUniversity(UniversityEntity universityEntity);

    /**
     * Получение списка позиций сетки расписания по учебному занятию.
     *
     * @param lessonNumber номер учебного занятия.
     * @return список позиций сетки расписания.
     */
    List<LessonGridEntity> findAllByLessonNumber(Integer lessonNumber);
}

