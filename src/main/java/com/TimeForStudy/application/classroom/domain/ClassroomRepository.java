package com.TimeForStudy.application.classroom.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link ClassroomEntity}
 */
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
}
