package com.TimeForStudy.application.university.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к учебным заведениям.
 *
 * @author Velikanov Artyom
 */
public interface UniversityService {

    /**
     * Получение информации об учебном заведении.
     *
     * @return UniversityDto информация об учебном заведении.
     */
    AddUniversityAndLessonGridDto getUniversity();

    /**
     * Редактирование данных об учебном заведении.
     *
     * @param addUniversityAndLessonGridDto информация об университете.
     * @return статус редактирования.
     */
    void updateUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto);

    /**
     * Получение списка рабочих дней недели.
     *
     * @return список преподавателей.
     */
    List<IdNameDto> findWorkDays();

    /**
     * Получение количества недель.
     *
     * @return количество недель.
     */
    Integer getWeeksNumber();

}
