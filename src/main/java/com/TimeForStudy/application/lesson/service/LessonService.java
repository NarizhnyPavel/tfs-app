package com.TimeForStudy.application.lesson.service;

import com.TimeForStudy.application.lesson.model.AddLessonDto;
import com.TimeForStudy.application.lesson.model.LessonDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности занятие
 *
 * @author Velikanov Artyom
 */
public interface LessonService {

    /**
     * Возвращение занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return занятие.
     */
    LessonDto getLessonById(long id);

    /**
     * Сохранение занятия.
     *
     * @param addLessonDto занятие.
     */
    void saveLesson(AddLessonDto addLessonDto);

    /**
     * Изменение значений занятия.
     *
     * @param id идентификатор.
     * @param addLessonDto занятие.
     */
    void updateLesson(long id, AddLessonDto addLessonDto);

    /**
     * Удаление занятия.
     *
     * @param id идентификатор.
     */
    void deleteLesson(long id);

    /**
     * Возвращение всех существующих занятий.
     *
     * @return список занятий.
     */
    List<LessonDto> findAll();
}
