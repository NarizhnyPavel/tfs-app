package com.TimeForStudy.application.lessongrid.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса запросов к месту занятия в сетке.
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
     * Получение всех существующих мест занятий в сетке.
     *
     * @return список мест занятий в сетке.
     */
    @Override
    public List<LessonGridDto> findAll() {
        return  lessonGridRepository.findAll().stream()
                .map(it -> LessonGridDto.of(it.getId(), it.getLessonNumber(), it.getTime()))
                .collect(Collectors.toList());
    }

    /**
     * Получение времени начала пар для выпадающего списка
     * в модели {@link IdNameDto}
     *
     * @return список времени пар.
     */
    @Override
    public List<IdNameDto> findTimes() {
        return lessonGridRepository.findAll().stream()
                .sorted(Comparator.comparingInt(LessonGridEntity::getLessonNumber))
                .map(lessonGrid -> IdNameDto.of(lessonGrid.getId(), lessonGrid.getTime()))
                .collect(Collectors.toList());
    }
}
