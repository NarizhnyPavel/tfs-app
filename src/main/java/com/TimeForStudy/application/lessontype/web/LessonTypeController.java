package com.TimeForStudy.application.lessontype.web;

import com.TimeForStudy.application.lessontype.model.AddLessonTypeDto;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.lessontype.service.LessonTypeService;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Обработчик запросов типа занятия.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class LessonTypeController {

    /**
     * {@link LessonTypeService}
     */
    private final LessonTypeService lessonTypeService;

    /**
     * Возвращает список типов занятий.
     *
     * @return список типов занятий.
     */
    @GetMapping(value = "/lessonType")
    public List<LessonTypeDto> getLessonTypes() {
        return lessonTypeService.findAll();
    }

    /**
     * Возвращает список типы пар.
     *
     * @return список типов пар.
     */
    @GetMapping(value = "/lessontypes")
    public List<UniversitiesDto> getLessonTypesLesson() {
        return lessonTypeService.findLessonTypes();
    }

    /**
     * Возвращает тип занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия
     */
    @GetMapping(value = "/lessonType/{id}")
    public LessonTypeDto getLessonType(@PathVariable long id) {
        return lessonTypeService.getLessonTypeById(id);
    }

    /**
     * Добавляет новый тип занятия.
     *
     * @param addLessonTypeDto тип занятия.
     */
    @PostMapping(value = "/lessonType/add")
    public void addLessonType(@RequestBody AddLessonTypeDto addLessonTypeDto) {
        lessonTypeService.saveLessonType(addLessonTypeDto);
    }

    /**
     * Изменяет данный тип занятия.
     *
     * @param id идентификатор.
     * @param addLessonTypeDto тип занятия.
     */
    @PutMapping(value = "/lessonType/update/{id}")
    public void updateLessonType(@PathVariable long id, @RequestBody AddLessonTypeDto addLessonTypeDto) {
        lessonTypeService.updateLessonType(id, addLessonTypeDto);
    }

    /**
     * Удаляет тип занятия.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/lessonType/delete/{id}")
    public void deleteLessonType(@PathVariable long id) {
        lessonTypeService.deleteLessonType(id);
    }
}
