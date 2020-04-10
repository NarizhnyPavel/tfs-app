package com.TimeForStudy.application.lessongrid.web;

import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * Возвращает список мест занятия в сетке.
     *
     * @return список мест занятия в сетке.
     */
    @GetMapping(value = "/lessonGrid")
    public List<LessonGridDto> getLessonGrids() {
        return lessonGridService.findAll();
    }

    /**
     * Возвращает место занятия в сетке по идентификатору.
     *
     * @param id идентификатор.
     * @return место занятия в сетке
     */
    @GetMapping(value = "/lessonGrid/{id}")
    public LessonGridDto getLessonGrid(@PathVariable long id) {
        return lessonGridService.getLessonGridById(id);
    }

    /**
     * Добавляет новое место занятия в сетке.
     *
     * @param addLessonGridDto место занятия в сетке.
     */
    @PostMapping(value = "/lessonGrid/add")
    public void addLessonGrid(@RequestBody AddLessonGridDto addLessonGridDto) {
        lessonGridService.saveLessonGrid(addLessonGridDto);
    }

    /**
     * Изменяет данное место занятия в сетке.
     *
     * @param id идентификатор.
     * @param addLessonGridDto место занятия в сетке.
     */
    @PutMapping(value = "/lessonGrid/update/{id}")
    public void updateLessonGrid(@PathVariable long id, @RequestBody AddLessonGridDto addLessonGridDto) {
        lessonGridService.updateLessonGrid(id, addLessonGridDto);
    }

    /**
     * Удаляет место занятия в сетке.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/lessonGrid/delete/{id}")
    public void deleteLessonGrid(@PathVariable long id) {
        lessonGridService.deleteLessonGrid(id);
    }
}
