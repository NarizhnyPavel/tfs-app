package com.TimeForStudy.application.classroom.web;

import com.TimeForStudy.application.classroom.service.ClassroomService;
import com.TimeForStudy.application.common.IdNameDto;
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
 * Обработчик запросов учебных помещений.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class ClassroomController {

    /**
     * {@link ClassroomService}
     */
    private final ClassroomService classroomService;

    /**
     * Получение списка всех учебных помещений.
     *
     * @return список учебных помещений.
     */
    @GetMapping(value = "/classroom")
    public List<IdNameDto> getClassrooms() {
        return classroomService.findAll();
    }

    /**
     * Получение списка учебных помещений по наименованию.
     *
     * @param name наименование учебного помещения.
     * @return список учебных помещений.
     */
    @PostMapping(value = "/classroom")
    public List<IdNameDto> postClassrooms(@RequestBody String name) {
        return classroomService.findAllClassrooms(name);
    }

    /**
     * Получение учебного помещения по идентификатору.
     *
     * @param id идентификатор учебного помещения.
     * @return учебное помещение.
     */
    @GetMapping(value = "/classroom/{id}")
    public IdNameDto getClassroom(@PathVariable Long id) {
        return classroomService.getClassroomById(id);
    }

    /**
     * Создание нового учебного помещения.
     *
     * @param classroom модель учебного помещения.
     */
    @PostMapping(value = "/admin/classroom")
    public void addClassroom(@RequestBody IdNameDto classroom) {
        classroomService.saveClassroom(classroom);
    }

    /**
     * Редактирование учебного помещения.
     *
     * @param id идентификатор учебного помещения.
     * @param classroom модель учебного помещения.
     */
    @PutMapping(value = "/admin/classroom/{id}")
    public void updateClassroom(@PathVariable Long id, @RequestBody IdNameDto classroom) {
        classroomService.updateClassroom(id, classroom);
    }

    /**
     * Удаление учебного помещения.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/admin/classroom/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
    }

}
