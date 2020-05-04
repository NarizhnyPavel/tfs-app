package com.TimeForStudy.application.university.service;

import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.user.model.ProfessorDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
public interface UniversityService {

    /**
     * Возвращение учебного заведения по идентификатору.
     *
     * @return учебное заведение.
     */
    AddUniversityAndLessonGridDto getUniversityById();

    /**
     * Сохранение учебного заведения.
     *
     * @param addUniversityAndLessonGridDto учебное заведение.
     */
    void saveUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto);

    /**
     * Изменение значений учебного заведения.
     *
     * @param addUniversityAndLessonGridDto учебное заведение.
     */
    void updateUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto);

    /**
     * Удаление учебного заведения.
     *
     * @param id идентификатор.
     */
    void deleteUniversity(long id);


    /**
     * Возвращение всех существующих учебных заведений.
     *
     * @return список учебных заведений.
     */
    List<UniversityDto> findAll();

    /**
     * Возвращение дни недели.
     *
     * @return дни недели.
     */
    List<UniversitiesDto> findWorkDays();

}
