package com.TimeForStudy.application.lesson.web;

import com.TimeForStudy.application.lesson.model.AddLessonDto;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Обработчик запросов занятия.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class LessonController {

    /**
     * {@link LessonService}
     */
    private final LessonService lessonService;

    /**
     * Возвращает список занятий.
     *
     * @return список занятий.
     */
    @GetMapping(value = "/lesson")
    public List<LessonDto> getLessons() {
        return lessonService.findAll();
    }

    /**
     * Возвращает занятие по идентификатору.
     *
     * @param id идентификатор.
     * @return занятие
     */
    @GetMapping(value = "/lesson/{id}")
    public LessonDto getLesson(@PathVariable long id) {
        return lessonService.getLessonById(id);
    }

    /**
     * Добавляет новое занятие.
     *
     * @param addLessonDto занятие.
     */
    @PostMapping(value = "/lesson/add")
    public void addLesson(@RequestBody AddLessonDto addLessonDto) {
        lessonService.saveLesson(addLessonDto);
    }

    /**
     * Изменяет данное занятие.
     *
     * @param id идентификатор.
     * @param addLessonDto занятие.
     */
    @PutMapping(value = "/lesson/update/{id}")
    public void updateLesson(@PathVariable long id, @RequestBody AddLessonDto addLessonDto) {
        lessonService.updateLesson(id, addLessonDto);
    }

    /**
     * Удаляет занятие.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/lesson/delete/{id}")
    public void deleteLesson(@PathVariable long id) {
        lessonService.deleteLesson(id);
    }

}
