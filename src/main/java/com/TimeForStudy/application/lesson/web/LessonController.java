package com.TimeForStudy.application.lesson.web;

import com.TimeForStudy.application.lesson.model.*;
import com.TimeForStudy.application.lesson.service.LessonService;
import com.TimeForStudy.application.user.model.UserDto;
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
     * Возвращение расписания занятий для студента.
     *
     * @param lessonStopDto информация о лекции.
     * @return статус.
     */
    @PostMapping(value = "lesson/stop")
    public String inLessonStop(@RequestBody LessonStopDto lessonStopDto) {
        return lessonService.inLessonStop(lessonStopDto);
    }

    /**
     * Отмена пары.
     *
     * @param addInfoLessonDto информация о лекции.
     * @return занятие.
     */
    @PostMapping(value = "lesson/info")
    public List<DaysDto> getLessonInfo(@RequestBody AddInfoLessonDto addInfoLessonDto) {
        return lessonService.getLessonInfo(addInfoLessonDto);
    }

    /**
     * Возвращение расписания из поиска.
     *
     * @param lessonByDto информация о лекции.
     * @return занятие.
     */
    @PostMapping(value = "lesson/by")
    public List<DaysDto> getLessonBy(@RequestBody LessonByDto lessonByDto) {
        return lessonService.getLessonBy(lessonByDto);
    }

    /**
     * Валидация в поиске.
     *
     * @param validateSearch наименование и тип.
     * @return список валидации.
     */
    @PostMapping(value = "/search")
    public  List<SearchDto> validatedSearch(@RequestBody ValidateSearch validateSearch) {
        return lessonService.getSearch(validateSearch);
    }

    /**
     * Возвращает данные для переноса лекции.
     *
     * @param id идентификатор.
     * @return занятие
     */
    @GetMapping(value = "/lesson/edit/{id}")
    public LessonEditInfo getLesson(@PathVariable long id) {
        return lessonService.getLessonEdit(id);
    }

    /**
     * Проверяет новое занятие.
     *
     * @param newLessonDto занятие.
     */
    @PostMapping(value = "/lesson/check")
    public List<BoolLessonDto> checkLesson(@RequestBody NewLessonDto newLessonDto) {
        return lessonService.checkLesson(newLessonDto);
    }

    /**
     * Добавляет новое занятие.
     *
     * @param newLessonDto занятие.
     */
    @PostMapping(value = "/lesson/add")
    public String addLesson(@RequestBody NewLessonDto newLessonDto) {
        return lessonService.addLesson(newLessonDto);
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
