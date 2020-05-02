package com.TimeForStudy.application.lessontype.service;

import com.TimeForStudy.application.lessontype.model.AddLessonTypeDto;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.university.model.UniversitiesDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности тип занятия
 *
 * @author Velikanov Artyom
 */
public interface LessonTypeService {

    /**
     * Возвращение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    LessonTypeDto getLessonTypeById(long id);

    /**
     * Сохранение типа занятия.
     *
     * @param addLessonTypeDto тип занятия.
     */
    void saveLessonType(AddLessonTypeDto addLessonTypeDto);

    /**
     * Изменение значений типа занятия.
     *
     * @param id идентификатор.
     * @param addLessonTypeDto тип занятия.
     */
    void updateLessonType(long id, AddLessonTypeDto addLessonTypeDto);

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    void deleteLessonType(long id);

    /**
     * Возвращение всех существующих типов занятия.
     *
     * @return список типов занятия.
     */
    List<LessonTypeDto> findAll();

    /**
     * Возвращение типы пар.
     *
     * @return типы пар.
     */
    List<UniversitiesDto> findLessonTypes();
}
