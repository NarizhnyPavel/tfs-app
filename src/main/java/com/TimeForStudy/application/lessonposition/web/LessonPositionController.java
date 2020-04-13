package com.TimeForStudy.application.lessonposition.web;

import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import com.TimeForStudy.application.lessonposition.model.AddLessonPositionDto;
import com.TimeForStudy.application.lessonposition.model.LessonPositionDto;
import com.TimeForStudy.application.lessonposition.service.LessonPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Обработчик запросов позиции лекции.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class LessonPositionController {

    /**
     * {@link LessonPositionService}
     */
    private final LessonPositionService lessonPositionService;

    /**
     * Возвращает список позиций лекций.
     *
     * @return список позиций лекции.
     */
    @GetMapping(value = "/lessonPositions")
    public List<LessonPositionDto> getLessonPositions() {
        return lessonPositionService.findAll();
    }

    /**
     * Возвращает позицию лекции по идентификатору.
     *
     * @param id идентификатор.
     * @return позиция лекции.
     */
    @GetMapping(value = "/lessonPosition/{id}")
    public LessonPositionDto getLessonPosition(@PathVariable long id) {
        return lessonPositionService.getLessonPositionById(id);
    }

    /**
     * Добавляет новую позицию лекции.
     *
     * @param addLessonPositionDto позиция лекции.
     */
    @PostMapping(value = "/lessonPosition/add")
    public void addLessonPosition(@RequestBody AddLessonPositionDto addLessonPositionDto) {
        lessonPositionService.saveLessonPosition(addLessonPositionDto);
    }

    /**
     * Изменяет данную позицию лекцию.
     *
     * @param id идентификатор.
     * @param addLessonPositionDto позиция лекции.
     */
    @PutMapping(value = "/lessonPosition/update/{id}")
    public void updateLessonPosition(@PathVariable long id, @RequestBody AddLessonPositionDto addLessonPositionDto) {
        lessonPositionService.updateLessonPosition(id, addLessonPositionDto);
    }

    /**
     * Удаляет позицию лекции.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/lessonPosition/delete/{id}")
    public void deleteLessonPosition(@PathVariable long id) {
        lessonPositionService.deleteLessonPosition(id);
    }
}
