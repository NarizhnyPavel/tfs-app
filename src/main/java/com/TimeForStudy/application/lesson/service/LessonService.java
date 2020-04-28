package com.TimeForStudy.application.lesson.service;

import com.TimeForStudy.application.lesson.model.*;

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
     * Возвращение расписания занятий на день
     *
     * @param addInfoLessonDto информация о расписании.
     * @return
     */
    DaysDto getLessonInfo(AddInfoLessonDto addInfoLessonDto);

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
