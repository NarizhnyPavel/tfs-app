package com.TimeForStudy.application.semester.web;

import com.TimeForStudy.application.semester.model.AddSemesterDto;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.semester.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SemesterController {

    /**
     * {@link SemesterService}
     */
    private final SemesterService semesterService;

    /**
     * Возвращает список семестров.
     *
     * @return список семестров.
     */
    @GetMapping(value = "/semester")
    public List<SemesterDto> getSemesters() {
        return semesterService.findAll();
    }

    /**
     * Возвращает семестр по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр
     */
    @GetMapping(value = "/semester/{id}")
    public SemesterDto getSemester(@PathVariable long id) {
        return semesterService.getSemesterById(id);
    }

    /**
     * Добавляет новый семестр.
     *
     * @param addSemesterDto семестр.
     */
    @PostMapping(value = "/semester/add")
    public void addSemester(@RequestBody AddSemesterDto addSemesterDto) {
        semesterService.saveSemester(addSemesterDto);
    }

    /**
     * Изменяет данный семестр.
     *
     * @param id идентификатор.
     * @param addSemesterDto семестр.
     */
    @PutMapping(value = "/semester/update/{id}")
    public void updateSemester(@PathVariable long id, @RequestBody AddSemesterDto addSemesterDto) {
        semesterService.updateSemester(id, addSemesterDto);
    }

    /**
     * Удаляет семестр.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/semester/delete/{id}")
    public void deleteSemester(@PathVariable long id) {
        semesterService.deleteSemester(id);
    }
}
