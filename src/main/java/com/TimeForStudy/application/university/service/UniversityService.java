package com.TimeForStudy.application.university.service;

import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;

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
     * @param id идентификатор.
     * @return учебное заведение.
     */
    UniversityDto getUniversityById(long id);

    /**
     * Сохранение учебного заведения.
     *
     * @param addUniversityDto учебное заведение.
     */
    void saveUniversity(AddUniversityDto addUniversityDto);

    /**
     * Изменение значений учебного заведения.
     *
     * @param id идентификатор.
     * @param addUniversityDto учебное заведение.
     */
    void updateUniversity(long id, AddUniversityDto addUniversityDto);

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
}