package com.TimeForStudy.application.lessontype.service.impl;

import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.model.AddLessonTypeDto;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.lessontype.service.LessonTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности тип занятия
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonTypeServiceImpl implements LessonTypeService {

    /**
     * {@link LessonTypeRepository}
     */
    private final LessonTypeRepository lessonTypeRepository;

    /**
     * Возвращение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    @Override
    public LessonTypeDto getLessonTypeById(long id) {
        return null;
    }

    /**
     * Сохранение типа занятия.
     *
     * @param addLessonTypeDto тип занятия.
     */
    @Override
    public void saveLessonType(AddLessonTypeDto addLessonTypeDto) {

    }

    /**
     * Изменение значений типа занятия.
     *
     * @param id идентификатор.
     * @param addLessonTypeDto тип занятия.
     */
    @Override
    public void updateLessonType(long id, AddLessonTypeDto addLessonTypeDto) {

    }

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonType(long id) {

    }

    /**
     * Возвращение всех существующих типов занятия.
     *
     * @return список типов занятия.
     */
    @Override
    public List<LessonTypeDto> findAll() {
        return null;
    }
}
