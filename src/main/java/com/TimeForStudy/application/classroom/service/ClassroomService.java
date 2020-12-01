package com.TimeForStudy.application.classroom.service;

import com.TimeForStudy.application.common.IdNameDto;

import java.util.List;

/**
 * Интерфейс сервиса учебных помещений.
 *
 * @author Velikanov Artyom
 */
public interface ClassroomService {

    /**
     * Получение учебного помещения по идентификатору.
     *
     * @param id идентификатор учебного помещения.
     * @return учебное помещение.
     */
    IdNameDto getClassroomById(Long id);

    /**
     * Создание нового учебного помещения.
     *
     * @param classroom модель учебного помещения.
     */
    void saveClassroom(IdNameDto classroom);

    /**
     * Редактирование учебного помещения.
     *
     * @param id идентификатор учебного помещения.
     * @param classroom модель учебного помещения.
     */
    void updateClassroom(Long id, IdNameDto classroom);

    /**
     * Удаление учебного помещения.
     *
     * @param id идентификатор.
     */
    void deleteClassroom(Long id);

    /**
     * Получение списка всех учебных помещений.
     *
     * @return список учебных помещений.
     */
    List<IdNameDto> findAll();

    /**
     * Получение списка учебных помещений по наименованию.
     *
     * @param name наименование учебного помещения.
     * @return список учебных помещений.
     */
    List<IdNameDto> findAllClassrooms(String name);
}
