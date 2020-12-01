package com.TimeForStudy.application.lessontype.service;

import com.TimeForStudy.application.common.IdNameDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к типам занятия.
 *
 * @author Velikanov Artyom
 */
public interface LessonTypeService {

    /**
     * Получение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    IdNameDto getLessonTypeById(Long id);

    /**
     * Добавление типа занятия.
     *
     * @param lessonType тип занятия.
     */
    void saveLessonType(IdNameDto lessonType);

    /**
     * Изменение типа занятия.
     *
     * @param id идентификатор.
     * @param lessonType тип занятия.
     */
    void updateLessonType(Long id, IdNameDto lessonType);

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    void deleteLessonType(Long id);

    /**
     * Получение списка типов занятий.
     *
     * @return список типов занятий.
     */
    List<IdNameDto> findAll();

    /**
     * Получение списка типов пар для выбора в выпадающем списке.
     *
     * @return список типов пар в модели {@link IdNameDto}.
     */
    List<IdNameDto> findLessonTypes();
}
