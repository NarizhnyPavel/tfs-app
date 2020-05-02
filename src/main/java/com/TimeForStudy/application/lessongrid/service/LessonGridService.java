package com.TimeForStudy.application.lessongrid.service;

import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.university.model.UniversitiesDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности место занятие в сетке
 *
 * @author Velikanov Artyom
 */
public interface LessonGridService {

    /**
     * Возвращение места занятия в сетке по идентификатору.
     *
     * @param id идентификатор.
     * @return место занятия в сетке.
     */
    LessonGridDto getLessonGridById(long id);

    /**
     * Сохранение места занятия в сетке.
     *
     * @param addLessonGridDto место занятия в сетке.
     */
    void saveLessonGrid(AddLessonGridDto addLessonGridDto);

    /**
     * Изменение значений места занятия в сетке.
     *
     * @param id идентификатор.
     * @param addLessonGridDto место занятия в сетке.
     */
    void updateLessonGrid(long id, AddLessonGridDto addLessonGridDto);

    /**
     * Удаление места занятия в сетке.
     *
     * @param id идентификатор.
     */
    void deleteLessonGrid(long id);

    /**
     * Возвращение всех существующих мест занятий в сетке.
     *
     * @return список мест занятий в сетке.
     */
    List<LessonGridDto> findAll();

    /**
     * Возвращение время пар.
     *
     * @return время пар.
     */
    List<UniversitiesDto> findTimes();
}
