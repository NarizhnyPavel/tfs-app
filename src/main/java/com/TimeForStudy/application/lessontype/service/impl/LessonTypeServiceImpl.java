package com.TimeForStudy.application.lessontype.service.impl;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import com.TimeForStudy.application.lessongrid.service.impl.LessonGridServiceImpl;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.model.AddLessonTypeDto;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.lessontype.service.LessonTypeService;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности тип занятия
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonTypeServiceImpl implements LessonTypeService {

    /**
     * {@link LessonTypeRepository}
     */
    private final LessonTypeRepository lessonTypeRepository;

    /**
     * Возвращение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    @Override
    public LessonTypeDto getLessonTypeById(long id) {
        LessonTypeEntity lessonTypeEntity = lessonTypeRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        return LessonTypeDto.of(lessonTypeEntity);
    }

    /**
     * Сохранение типа занятия.
     *
     * @param addLessonTypeDto тип занятия.
     */
    @Override
    public void saveLessonType(AddLessonTypeDto addLessonTypeDto) {
        LessonTypeEntity lessonTypeEntity = new LessonTypeEntity(addLessonTypeDto);
        lessonTypeRepository.save(lessonTypeEntity);
    }

    /**
     * Изменение значений типа занятия.
     *
     * @param id идентификатор.
     * @param addLessonTypeDto тип занятия.
     */
    @Override
    public void updateLessonType(long id, AddLessonTypeDto addLessonTypeDto) {
        LessonTypeEntity updated = lessonTypeRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        if (addLessonTypeDto.getName()!=null) {
            updated.setName(addLessonTypeDto.getName());
        }
        lessonTypeRepository.save(updated);
    }

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonType(long id) {
        lessonTypeRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих типов занятия.
     *
     * @return список типов занятия.
     */
    @Override
    public List<LessonTypeDto> findAll() {
        List<LessonTypeEntity> lessonTypeEntities = lessonTypeRepository.findAll();
        return lessonTypeEntities.stream().map(LessonTypeDto::of).collect(Collectors.toList());
    }

    /**
     * Возвращение типы пар.
     *
     * @return типы пар.
     */
    @Override
    public List<UniversitiesDto> findLessonTypes() {
        List<UniversitiesDto> universitiesDtos = new ArrayList<>();
        List<LessonTypeEntity> lessonTypeEntities= lessonTypeRepository.findAll();

        for (LessonTypeEntity lessonType : lessonTypeEntities) {
            universitiesDtos.add(new UniversitiesDto(lessonType.getId(),lessonType.getName()));
        }
        return universitiesDtos;
    }
}
