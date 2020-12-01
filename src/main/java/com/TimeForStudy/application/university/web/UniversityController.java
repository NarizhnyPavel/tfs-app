package com.TimeForStudy.application.university.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * Получение количества недель.
     *
     * @return количество недель.
     */
    @GetMapping(value = "/university/weeks")
    public Integer getWeeks() {
        return universityService.getWeeksNumber();
    }

    /**
     * Получение списка рабочих дней недели.
     *
     * @return список преподавателей.
     */
    @GetMapping(value = "/university/workdays")
    public List<IdNameDto> postWorkDays() {
        return universityService.findWorkDays();
    }

    /**
     * Получение информации об учебном заведении.
     *
     * @return UniversityDto информация об учебном заведении.
     */
    @GetMapping(value = "/university")
    public AddUniversityAndLessonGridDto getUniversity() {
        return universityService.getUniversity();
    }

    /**
     * Редактирование данных об учебном заведении.
     *
     * @param addUniversityAndLessonGridDto информация об университете.
     * @return статус редактирования.
     */
    @PutMapping(value = "/admin/university")
    public String updateUniversity(@RequestBody AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        universityService.updateUniversity(addUniversityAndLessonGridDto);
        return "success";
    }

}
