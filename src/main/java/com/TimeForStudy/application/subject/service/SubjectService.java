package com.TimeForStudy.application.subject.service;

import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.model.SubjectsDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности преподаваемая дисциплина
 *
 * @author Velikanov Artyom
 */
public interface SubjectService {

    /**
     * Возвращение преподаваемой дисциплины по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина.
     */
    SubjectDto getSubjectById(long id);

    /**
     * Сохранение преподаваемой дисциплины.
     *
     * @param addSubjectDto преподаваемая дисциплина.
     */
    void saveSubject(AddSubjectDto addSubjectDto);

    /**
     * Изменение значений преподаваемой дисциплины.
     *
     * @param id идентификатор.
     * @param addSubjectDto преподаваемая дисциплина.
     */
    void updateSubject(long id, AddSubjectDto addSubjectDto);

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    void deleteSubject(long id);

    /**
     * Возвращение всех существующих преподаваемых дисциплин.
     *
     * @return список преподаваемый дисциплин.
     */
    List<SubjectDto> findAll();

    /**
     * Возвращение всех существующих преподаваемых дисциплин.
     *
     * @return список преподаваемый дисциплин.
     */
    List<SubjectsDto> findAllSubjects(String name);
}
