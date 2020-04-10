package com.TimeForStudy.application.semester.service;

import com.TimeForStudy.application.semester.model.AddSemesterDto;
import com.TimeForStudy.application.semester.model.SemesterDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности семестр
 *
 * @author Velikanov Artyom
 */
public interface SemesterService {

    /**
     * Возвращение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    SemesterDto getSemesterById(long id);

    /**
     * Сохранение семестра.
     *
     * @param addSemesterDto семестр.
     */
    void saveSemester(AddSemesterDto addSemesterDto);

    /**
     * Изменение значений семестра.
     *
     * @param id идентификатор.
     * @param addSemesterDto семестр.
     */
    void updateSemester(long id, AddSemesterDto addSemesterDto);

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    void deleteSemester(long id);

    /**
     * Возвращение всех существующих семестров.
     *
     * @return список семестров.
     */
    List<SemesterDto> findAll();
}
