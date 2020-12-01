package com.TimeForStudy.application.subject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link SubjectEntity}
 */
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    /**
     * Получение списка учебных дисциплин по наименованию.
     *
     * @param name наименование учебной дисциплины.
     * @return список учебных дисциплин.
     */
    List<SubjectEntity> findAllByName(String name);

}
