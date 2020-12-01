package com.TimeForStudy.application.semester.service;

import com.TimeForStudy.application.semester.model.SemesterDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к семестрам.
 *
 * @author Velikanov Artyom
 */
public interface SemesterService {

    /**
     * Получение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    SemesterDto getSemesterById(Long id);

    /**
     * Добавление семестра.
     *
     * @param semester семестр.
     */
    void saveSemester(SemesterDto semester);

    /**
     * Редактирование семестра.
     *
     * @param id идентификатор.
     * @param semester семестр.
     */
    void updateSemester(Long id, SemesterDto semester);

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    void deleteSemester(Long id);

    /**
     * Получение списка семестров.
     *
     * @return список семестров.
     */
    List<SemesterDto> findAll();
}
