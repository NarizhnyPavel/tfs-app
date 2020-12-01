package com.TimeForStudy.application.semester.web;

import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.semester.service.SemesterService;
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
 * Обработчик запросов семестра.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class SemesterController {

    /**
     * {@link SemesterService}
     */
    private final SemesterService semesterService;

    /**
     * Получение списка семестров.
     *
     * @return список семестров.
     */
    @GetMapping(value = "/admin/semester")
    public List<SemesterDto> getSemesters() {
        return semesterService.findAll();
    }

    /**
     * Получение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    @GetMapping(value = "/admin/semester/{id}")
    public SemesterDto getSemester(@PathVariable Long id) {
        return semesterService.getSemesterById(id);
    }

    /**
     * Добавление семестра.
     *
     * @param semester семестр.
     */
    @PostMapping(value = "/admin/semester")
    public void addSemester(@RequestBody SemesterDto semester) {
        semesterService.saveSemester(semester);
    }

    /**
     * Редактирование семестра.
     *
     * @param id идентификатор.
     * @param semester семестр.
     */
    @PutMapping(value = "/admin/semester/{id}")
    public void updateSemester(@PathVariable Long id, @RequestBody SemesterDto semester) {
        semesterService.updateSemester(id, semester);
    }

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/admin/semester/{id}")
    public void deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemester(id);
    }
}
