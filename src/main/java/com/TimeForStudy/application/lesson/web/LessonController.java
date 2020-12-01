package com.TimeForStudy.application.lesson.web;

import com.TimeForStudy.application.lesson.model.BoolLessonDto;
import com.TimeForStudy.application.lesson.model.DayDto;
import com.TimeForStudy.application.lesson.model.GetInfoLessonDto;
import com.TimeForStudy.application.lesson.model.LessonByDto;
import com.TimeForStudy.application.lesson.model.LessonEditInfo;
import com.TimeForStudy.application.lesson.model.LessonStopDto;
import com.TimeForStudy.application.lesson.model.NewLessonDto;
import com.TimeForStudy.application.lesson.model.SearchDto;
import com.TimeForStudy.application.lesson.model.UpdatePosition;
import com.TimeForStudy.application.lesson.model.ValidateSearch;
import com.TimeForStudy.application.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * Отмена занятия.
     *
     * @param lessonStopDto информация о занятии.
     * @return статус.
     */
    @PostMapping(value = "/teacher/lesson/stop")
    public String inLessonStop(@RequestBody LessonStopDto lessonStopDto) {
        return lessonService.inLessonStop(lessonStopDto);
    }

    /**
     * Получение расписание занятий для главной страницы пользователя.
     *
     * @param getInfoLessonDto информация о занятие.
     * @return расписание занятий.
     */
    @PostMapping(value = "lesson/info")
    public List<DayDto> getLessonInfo(@RequestBody GetInfoLessonDto getInfoLessonDto) {
        return lessonService.getLessonInfo(getInfoLessonDto);
    }

    /**
     * Получение расписания из поиска.
     *
     * @param lessonByDto информация о занятие.
     * @return расписание занятий.
     */
    @PostMapping(value = "lesson/by")
    public List<DayDto> getLessonBy(@RequestBody LessonByDto lessonByDto) {
        return lessonService.getLessonBy(lessonByDto);
    }

    /**
     * Валидация в поиске.
     *
     * @param validateSearch наименование и тип.
     * @return список валидации.
     */
    @PostMapping(value = "/search")
    public List<SearchDto> validatedSearch(@RequestBody ValidateSearch validateSearch) {
        return lessonService.getSearch(validateSearch);
    }

    /**
     * Получение данных для переноса занятия.
     *
     * @param id идентификатор занятия.
     * @return занятие.
     */
    @GetMapping(value = "/teacher/lesson/{id}")
    public LessonEditInfo getLesson(@PathVariable Long id) {
        return lessonService.getLessonEdit(id);
    }

    /**
     * Проверка новых значений для занятия.
     *
     * @param newLessonDto занятие.
     * @return список признаков валидации данных.
     */
    @PostMapping(value = "/teacher/lesson/check")
    public List<BoolLessonDto> checkLesson(@RequestBody NewLessonDto newLessonDto) {
        return lessonService.checkLesson(newLessonDto);
    }

    /**
     * Добавляет новое занятие.
     *
     * @param newLessonDto занятие.
     * @return статус добавления занятия.
     */
    @PostMapping(value = "/admin/lesson")
    public String addLesson(@RequestBody NewLessonDto newLessonDto) {
        return lessonService.addLesson(newLessonDto);
    }

    /**
     * Перенос занятия.
     *
     * @param updatePosition данные для переноса.
     * @return статус переноса занятия.
     */
    @PutMapping(value = "/teacher/lesson/move")
    public String updateLesson(@RequestBody UpdatePosition updatePosition) {
        return lessonService.updateLesson(updatePosition);
    }

    /**
     * Удаление занятие.
     *
     * @param id идентификатор занятия.
     * @return статус удаления занятия.
     */
    @DeleteMapping(value = "/admin/lesson/{id}")
    public String deleteLesson(@PathVariable Long id) {
        return lessonService.deleteLesson(id);
    }

}
