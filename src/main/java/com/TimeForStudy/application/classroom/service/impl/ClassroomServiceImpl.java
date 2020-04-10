package com.TimeForStudy.application.classroom.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.classroom.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности кабинет
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    /**
     * {@link ClassroomRepository}
     */
    private final ClassroomRepository classroomRepository;

    /**
     * Возвращение кабинета по идентификатору.
     *
     * @param id идентификатор.
     * @return кабинет.
     */
    @Override
    public ClassroomDto getClassroomById(long id) {
        return null;
    }

    /**
     * Сохранение кабинета.
     *
     * @param addClassroomDto кабинет.
     */
    @Override
    public void saveClassroom(AddClassroomDto addClassroomDto) {

    }

    /**
     * Изменение значений кабинета.
     *
     * @param id идентификатор.
     * @param addClassroomDto кабинет.
     */
    @Override
    public void updateClassroom(long id, AddClassroomDto addClassroomDto) {

    }

    /**
     * Удаление кабинета.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteClassroom(long id) {

    }

    /**
     * Возвращение всех существующих кабинетов.
     *
     * @return список кабинетов.
     */
    @Override
    public List<ClassroomDto> findAll() {
        return null;
    }
}
