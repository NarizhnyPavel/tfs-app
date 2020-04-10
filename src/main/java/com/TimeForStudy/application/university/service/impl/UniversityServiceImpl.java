package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

    /**
     * Возвращение учебного заведения по идентификатору.
     *
     * @param id идентификатор.
     * @return учебное заведение.
     */
    @Override
    public UniversityDto getUniversityById(long id) {
        return null;
    }

    /**
     * Сохранение учебного заведения.
     *
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void saveUniversity(AddUniversityDto addUniversityDto) {

    }

    /**
     * Изменение значений учебного заведения.
     *
     * @param id идентификатор.
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void updateUniversity(long id, AddUniversityDto addUniversityDto) {

    }

    /**
     * Удаление учебного заведения.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUniversity(long id) {

    }

    /**
     * Возвращение всех существующих учебных заведений.
     *
     * @return список учебных заведений.
     */
    @Override
    public List<UniversityDto> findAll() {
        return null;
    }
}
