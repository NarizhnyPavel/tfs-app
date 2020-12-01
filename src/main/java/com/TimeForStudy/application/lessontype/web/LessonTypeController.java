package com.TimeForStudy.application.lessontype.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessontype.service.LessonTypeService;
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
     * Получение списка типов занятий.
     *
     * @return список типов занятий.
     */
    //TODO разобраться почему имеется 2 одинаковых эндпоинта
    @GetMapping(value = "/lesson_type")
    public List<IdNameDto> getLessonTypes() {
        return lessonTypeService.findAll();
    }

    /**
     * Получение списка типов пар для выбора в выпадающем списке.
     *
     * @return список типов пар в модели {@link IdNameDto}.
     */
    @GetMapping(value = "/lesson_types")
    public List<IdNameDto> getLessonTypesLesson() {
        return lessonTypeService.findLessonTypes();
    }

    /**
     * Получение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    @GetMapping(value = "/lesson_type/{id}")
    public IdNameDto getLessonType(@PathVariable Long id) {
        return lessonTypeService.getLessonTypeById(id);
    }

    /**
     * Добавление типа занятия.
     *
     * @param lessonType тип занятия.
     */
    @PostMapping(value = "/admin/lesson_type")
    public void addLessonType(@RequestBody IdNameDto lessonType) {
        lessonTypeService.saveLessonType(lessonType);
    }

    /**
     * Изменение типа занятия.
     *
     * @param id идентификатор.
     * @param lessonType тип занятия.
     */
    @PutMapping(value = "/admin/lesson_type/{id}")
    public void updateLessonType(@PathVariable Long id, @RequestBody IdNameDto lessonType) {
        lessonTypeService.updateLessonType(id, lessonType);
    }

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/admin/lesson_type/{id}")
    public void deleteLessonType(@PathVariable Long id) {
        lessonTypeService.deleteLessonType(id);
    }
}
