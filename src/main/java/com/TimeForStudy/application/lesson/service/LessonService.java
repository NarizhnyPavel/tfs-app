package com.TimeForStudy.application.lesson.service;

import com.TimeForStudy.application.lesson.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * Возвращение расписания занятий для преподавателя.
     *
     * @param addInfoLessonDto информация о расписании.
     * @return список дней.
     */
    List<DaysDto> getLessonInfo(AddInfoLessonDto addInfoLessonDto);

    /**
     * Возвращение расписания занятий для студента.
     *
     * @param lessonStopDto информация о лекции.
     * @return статус.
     */
    String inLessonStop(LessonStopDto lessonStopDto);

    /**
     * Возвращение расписания на поиск.
     *
     * @param lessonByDto информация о расписании.
     * @return список дней.
     */
    List<DaysDto> getLessonBy(LessonByDto lessonByDto);

    /**
     * Валидация поиска.
     *
     * @param validateSearch строка валидации и тип.
     * @return список поиска.
     */
    List<SearchDto> getSearch(ValidateSearch validateSearch);

    /**
     * Сохранение занятия.
     *
     * @param newLessonDto занятие.
     */
    List<BoolLessonDto> saveLesson(NewLessonDto newLessonDto);

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
