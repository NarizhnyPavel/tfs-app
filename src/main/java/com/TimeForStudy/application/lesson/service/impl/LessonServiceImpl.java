package com.TimeForStudy.application.lesson.service.impl;

import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.AddLessonDto;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности занятие
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    /**
     * {@link LessonRepository}
     */
    private final LessonRepository lessonRepository;

    /**
     * Возвращение занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return занятие.
     */
    @Override
    public LessonDto getLessonById(long id) {
        return null;
    }

    /**
     * Сохранение занятия.
     *
     * @param addLessonDto занятие.
     */
    @Override
    public void saveLesson(AddLessonDto addLessonDto) {

    }

    /**
     * Изменение значений занятия.
     *
     * @param id идентификатор.
     * @param addLessonDto занятие.
     */
    @Override
    public void updateLesson(long id, AddLessonDto addLessonDto) {

    }

    /**
     * Удаление занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLesson(long id) {

    }

    /**
     * Возвращение всех существующих занятий.
     *
     * @return список занятий.
     */
    @Override
    public List<LessonDto> findAll() {
        return null;
    }
}
