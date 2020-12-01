package com.TimeForStudy.application.lessongrid.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Обработчик запросов теста занятия в сетке.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class LessonGridController {

    /**
     * {@link LessonGridService}
     */
    private final LessonGridService lessonGridService;

    /**
     * Получение времени начала пар для выпадающего списка
     * в модели {@link IdNameDto}
     *
     * @return список времени пар.
     */
    @GetMapping(value = "/times")
    public List<IdNameDto> getTimes() {
        return lessonGridService.findTimes();
    }

}
