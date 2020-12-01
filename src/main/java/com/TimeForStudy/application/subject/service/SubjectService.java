package com.TimeForStudy.application.subject.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.subject.model.SubjectDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к преподаваемым дисциплинам.
 *
 * @author Velikanov Artyom
 */
public interface SubjectService {

    /**
     * Получение преподаваемой дисциплины по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина
     */
    SubjectDto getSubjectById(Long id);

    /**
     * Добавление преподаваемой дисциплины.
     *
     * @param subject преподаваемая дисциплина.
     */
    void saveSubject(SubjectDto subject);

    /**
     * Редактирование преподаваемой дисциплины.
     *
     * @param id идентификатор.
     * @param subjectDto преподаваемая дисциплина.
     */
    void updateSubject(Long id, SubjectDto subjectDto);

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    void deleteSubject(Long id);

    /**
     * Получение списка преподаваемых дисциплин.
     *
     * @return список преподаваемых дисциплин.
     */
    List<SubjectDto> findAll();

    /**
     * Получение дисциплин по наименованию.
     *
     * @param name наименовае дисциплины.
     * @return список преподаваемых дисциплин.
     */
    List<IdNameDto> findAllSubjects(String name);
}
