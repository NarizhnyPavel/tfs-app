package com.TimeForStudy.application.lesson.service;

import com.TimeForStudy.application.lesson.model.*;

import java.util.List;

/**
 * Интерфейс сервис запросов занятия.
 *
 * @author Velikanov Artyom
 */
public interface LessonService {

    /**
     * Получение данных для переноса занятия.
     *
     * @param id идентификатор занятия.
     * @return занятие.
     */
    LessonEditInfo getLessonEdit(Long id);

    /**
     * Получение расписание занятий для главной страницы пользователя.
     *
     * @param getInfoLessonDto информация о занятие.
     * @return расписание занятий.
     */
    List<DayDto> getLessonInfo(GetInfoLessonDto getInfoLessonDto);

    /**
     * Отмена занятия.
     *
     * @param lessonStopDto информация о занятии.
     * @return статус.
     */
    String inLessonStop(LessonStopDto lessonStopDto);

    /**
     * Получение расписания из поиска.
     *
     * @param lessonByDto информация о занятие.
     * @return расписание занятий.
     */
    List<DayDto> getLessonBy(LessonByDto lessonByDto);

    /**
     * Валидация в поиске.
     *
     * @param validateSearch наименование и тип.
     * @return список валидации.
     */
    List<SearchDto> getSearch(ValidateSearch validateSearch);

    /**
     * Проверка новых значений для занятия.
     *
     * @param newLessonDto занятие.
     * @return список признаков валидации данных.
     */
    List<BoolLessonDto> checkLesson(NewLessonDto newLessonDto);

    /**
     * Добавляет новое занятие.
     *
     * @param newLessonDto занятие.
     * @return статус добавления занятия.
     */
    String addLesson(NewLessonDto newLessonDto);

    /**
     * Перенос занятия.
     *
     * @param updatePosition данные для переноса.
     * @return статус переноса занятия.
     */
    String updateLesson(UpdatePosition updatePosition);

    /**
     * Удаление занятие.
     *
     * @param id идентификатор занятия.
     * @return статус удаления занятия.
     */
    String deleteLesson(Long id);

}
