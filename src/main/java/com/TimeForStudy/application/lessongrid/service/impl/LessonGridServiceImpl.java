package com.TimeForStudy.application.lessongrid.service.impl;

import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности место занятие в сетке
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonGridServiceImpl implements LessonGridService {

    /**
     * {@link LessonGridRepository}
     */
    private final LessonGridRepository lessonGridRepository;

    /**
     * Возвращение места занятия в сетке по идентификатору.
     *
     * @param id идентификатор.
     * @return место занятия в сетке.
     */
    @Override
    public LessonGridDto getLessonGridById(long id) {
        return null;
    }

    /**
     * Сохранение места занятия в сетке.
     *
     * @param addLessonGridDto место занятия в сетке.
     */
    @Override
    public void saveLessonGrid(AddLessonGridDto addLessonGridDto) {

    }

    /**
     * Изменение значений места занятия в сетке.
     *
     * @param id идентификатор.
     * @param addLessonGridDto место занятия в сетке.
     */
    @Override
    public void updateLessonGrid(long id, AddLessonGridDto addLessonGridDto) {

    }

    /**
     * Удаление места занятия в сетке.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonGrid(long id) {

    }

    /**
     * Возвращение всех существующих мест занятий в сетке.
     *
     * @return список мест занятий в сетке.
     */
    @Override
    public List<LessonGridDto> findAll() {
        return null;
    }
}
