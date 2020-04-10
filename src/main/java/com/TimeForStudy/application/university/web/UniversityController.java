package com.TimeForStudy.application.university.web;

import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityController {

    /**
     * {@link UniversityService}
     */
    private final UniversityService universityService;

    /**
     * Возвращает список занятий.
     *
     * @return список занятий.
     */
    @GetMapping(value = "/university")
    public List<UniversityDto> getUniversities() {
        return universityService.findAll();
    }

    /**
     * Возвращает занятие по идентификатору.
     *
     * @param id идентификатор.
     * @return занятие
     */
    @GetMapping(value = "/university/{id}")
    public UniversityDto getUniversity(@PathVariable long id) {
        return universityService.getUniversityById(id);
    }

    /**
     * Добавляет новое занятие.
     *
     * @param addUniversityDto занятие.
     */
    @PostMapping(value = "/university/add")
    public void addUniversity(@RequestBody AddUniversityDto addUniversityDto) {
        universityService.saveUniversity(addUniversityDto);
    }

    /**
     * Изменяет данное занятие.
     *
     * @param id идентификатор.
     * @param addUniversityDto занятие.
     */
    @PutMapping(value = "/university/update/{id}")
    public void updateUniversity(@PathVariable long id, @RequestBody AddUniversityDto addUniversityDto) {
        universityService.updateUniversity(id, addUniversityDto);
    }

    /**
     * Удаляет занятие.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/university/delete/{id}")
    public void deleteUniversity(@PathVariable long id) {
        universityService.deleteUniversity(id);
    }
}
