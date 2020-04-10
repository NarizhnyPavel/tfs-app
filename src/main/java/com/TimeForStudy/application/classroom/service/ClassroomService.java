package com.TimeForStudy.application.classroom.service;

import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.classroom.model.ClassroomDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности кабинет
 *
 * @author Velikanov Artyom
 */
public interface ClassroomService {

    /**
     * Возвращение кабинета по идентификатору.
     *
     * @param id идентификатор.
     * @return кабинет.
     */
    ClassroomDto getClassroomById(long id);

    /**
     * Сохранение кабинета.
     *
     * @param addClassroomDto кабинет.
     */
    void saveClassroom(AddClassroomDto addClassroomDto);

    /**
     * Изменение значений кабинета.
     *
     * @param id идентификатор.
     * @param addClassroomDto кабинет.
     */
    void updateClassroom(long id, AddClassroomDto addClassroomDto);

    /**
     * Удаление кабинета.
     *
     * @param id идентификатор.
     */
    void deleteClassroom(long id);

    /**
     * Возвращение всех существующих кабинетов.
     *
     * @return список кабинетов.
     */
    List<ClassroomDto> findAll();
}
