package com.TimeForStudy.application.university.web;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Обработчик запросов учебного заведения.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class UniversityController {

    /**
     * {@link UniversityService}
     */
    private final UniversityService universityService;

    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

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
     * Возвращает количество недель.
     *
     * @return количество недель.
     */
    @GetMapping(value = "/university/weeks")
    public int getWeeks() {
        UniversityEntity universityEntity = universityRepository.findById((long) 1).orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        return universityEntity.getWeeks();
    }

    /**
     * Возвращает список преподавателей.
     *
     * @return список преподавателей.
     */
    @PostMapping(value = "/workdays")
    public List<UniversitiesDto> postWorkDays() {
        return universityService.findWorkDays();
    }


    /**
     * Возвращает занятие по идентификатору.
     *
     * @param id идентификатор.
     * @return lessontypesзанятие
     */
    @GetMapping(value = "/university/{id}")
    public UniversityDto getUniversity(@PathVariable long id) {
        return universityService.getUniversityById(id);
    }

    /**
     * Добавляет новое занятие.
     *
     * @param addUniversityAndLessonGridDto занятие.
     */
    @PostMapping(value = "university/add")
    public String addUniversity(@RequestBody AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        universityService.saveUniversity(addUniversityAndLessonGridDto);
        return "success";
    }

    /**
     * Изменяет данное занятие.
     *
     * @param id идентификатор.
     * @param addUniversityDto занятие.
     */
    @PostMapping(value = "/university/update/{id}")
    public String updateUniversity(@PathVariable long id, @RequestBody AddUniversityDto addUniversityDto) {
        universityService.updateUniversity(id, addUniversityDto);
        return "success";
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
