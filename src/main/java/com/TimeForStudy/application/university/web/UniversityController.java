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
    @GetMapping(value = "/universities")
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
     * Возвращает информации об унивкрситете
     * .
     *
     * @return UniversityDto информация об университете
     */
    @GetMapping(value = "/university")
    public AddUniversityAndLessonGridDto getUniversity() {
        return universityService.getUniversityById();
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
     * Изменяет данные об университете.
     *
     * @param addUniversityAndLessonGridDto информация об университете.
     */
    @PostMapping(value = "/university/update")
    public String updateUniversity(@RequestBody AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        universityService.updateUniversity(addUniversityAndLessonGridDto);
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
