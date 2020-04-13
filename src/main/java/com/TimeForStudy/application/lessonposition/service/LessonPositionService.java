package com.TimeForStudy.application.lessonposition.service;

import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessonposition.model.AddLessonPositionDto;
import com.TimeForStudy.application.lessonposition.model.LessonPositionDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
public interface LessonPositionService {

    /**
     * Возвращение позиции лекции по идентификатору.
     *
     * @param id идентификатор.
     * @return позиция лекции.
     */
    LessonPositionDto getLessonPositionById(long id);

    /**
     * Сохранение позиции лекции.
     *
     * @param addLessonPositionDto позиция лекции
     */
    void saveLessonPosition(AddLessonPositionDto addLessonPositionDto);

    /**
     * Изменение значений позиции лекции.
     *
     * @param id идентификатор.
     * @param addLessonPositionDto позиция лекции.
     */
    void updateLessonPosition(long id, AddLessonPositionDto addLessonPositionDto);

    /**
     * Удаление позиции лекции.
     *
     * @param id идентификатор.
     */
    void deleteLessonPosition(long id);

    /**
     * Возвращение всех существующих позиций лекции.
     *
     * @return список позиций лекции.
     */
    List<LessonPositionDto> findAll();
}
