package com.TimeForStudy.application.lessongrid.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link LessonGridEntity}
 */
public interface LessonGridRepository extends JpaRepository<LessonGridEntity, Long> {
}
