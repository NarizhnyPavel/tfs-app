package com.TimeForStudy.application.lessongrid.domain;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link LessonGridEntity}
 */
public interface LessonGridRepository extends JpaRepository<LessonGridEntity, Long> {

    List<LessonGridEntity> findAllByUniversity(UniversityEntity universityEntity);

}

