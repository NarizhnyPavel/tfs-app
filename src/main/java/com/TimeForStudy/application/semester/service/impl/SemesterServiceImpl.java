package com.TimeForStudy.application.semester.service.impl;

import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.AddSemesterDto;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.semester.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности семестр
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    /**
     * {@link SemesterRepository}
     */
    private final SemesterRepository semesterRepository;

    /**
     * Возвращение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    @Override
    public SemesterDto getSemesterById(long id) {
        return null;
    }

    /**
     * Сохранение семестра.
     *
     * @param addSemesterDto семестр.
     */
    @Override
    public void saveSemester(AddSemesterDto addSemesterDto) {

    }

    /**
     * Изменение значений семестра.
     *
     * @param id идентификатор.
     * @param addSemesterDto семестр.
     */
    @Override
    public void updateSemester(long id, AddSemesterDto addSemesterDto) {

    }

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSemester(long id) {

    }

    /**
     * Возвращение всех существующих семестров.
     *
     * @return список семестров.
     */
    @Override
    public List<SemesterDto> findAll() {
        return null;
    }
}
