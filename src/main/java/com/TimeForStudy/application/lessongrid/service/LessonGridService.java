package com.TimeForStudy.application.lessongrid.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к сущности место занятие в сетке.
 *
 * @author Velikanov Artyom
 */
public interface LessonGridService {

    /**
     * Получение всех существующих мест занятий в сетке.
     *
     * @return список мест занятий в сетке.
     */
    List<LessonGridDto> findAll();

    /**
     * Получение времени начала пар для выпадающего списка
     * в модели {@link IdNameDto}
     *
     * @return список времени пар.
     */
    List<IdNameDto> findTimes();
}
