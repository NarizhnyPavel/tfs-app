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
     * Возвращает данные для переноса лекции.
     *
     * @param id идентификатор.
     * @return занятие
     */
    LessonEditInfo getLessonEdit(long id);

    /**
     * Возвращение расписания занятий для преподавателя.
     *
     * @param addInfoLessonDto информация о расписании.
     * @return список дней.
     */
    List<DayDto> getLessonInfo(AddInfoLessonDto addInfoLessonDto);

    /**
     * Отмена занятия на неделю.
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
    List<DayDto> getLessonBy(LessonByDto lessonByDto);

    /**
     * Валидация поиска.
     *
     * @param validateSearch строка валидации и тип.
     * @return список поиска.
     */
    List<SearchDto> getSearch(ValidateSearch validateSearch);

    /**
     * Провеняет занятия.
     *
     * @param newLessonDto занятие.
     */
    List<BoolLessonDto> checkLesson(NewLessonDto newLessonDto);

    /**
     * Сохранение занятия.
     *
     * @param newLessonDto занятие.
     */
    String addLesson(NewLessonDto newLessonDto);

    /**
     * Изменение значений занятия.
     *
     * @param updatePosition Обновлённые данные.
     */
    String updateLesson(UpdatePosition updatePosition);

    /**
     * Удаление занятия.
     *
     * @param id идентификатор.
     */
    String deleteLesson(long id);

    /**
     * Возвращение всех существующих занятий.
     *
     * @return список занятий.
     */
    List<LessonDto> findAll();
}
