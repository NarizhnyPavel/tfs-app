package com.TimeForStudy.application.classroom.web;

import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.classroom.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Обработчик запросов кабинета.
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
     * Возвращает список кабинетов.
     *
     * @return список кабинетов.
     */
    @GetMapping(value = "/classroom")
    public List<ClassroomDto> getClassrooms() {
        return classroomService.findAll();
    }

    /**
     * Возвращает кабинет по идентификатору.
     *
     * @param id идентификатор.
     * @return кабинет
     */
    @GetMapping(value = "/classroom/{id}")
    public ClassroomDto getClassroom(@PathVariable long id) {
        return classroomService.getClassroomById(id);
    }

    /**
     * Добавляет новый кабинет.
     *
     * @param addClassroomDto кабинет.
     */
    @PostMapping(value = "/classroom/add")
    public void addClassroom(@RequestBody AddClassroomDto addClassroomDto) {
        classroomService.saveClassroom(addClassroomDto);
    }

    /**
     * Изменяет данный кабинет.
     *
     * @param id идентификатор.
     * @param addClassroomDto кабинет.
     */
    @PutMapping(value = "/classroom/update/{id}")
    public void updateClassroom(@PathVariable long id, @RequestBody AddClassroomDto addClassroomDto) {
        classroomService.updateClassroom(id, addClassroomDto);
    }

    /**
     * Удаляет кабинет.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/classroom/delete/{id}")
    public void deleteClassroom(@PathVariable long id) {
        classroomService.deleteClassroom(id);
    }

}
