package com.TimeForStudy.application.lessontype.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.service.LessonTypeService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Реализация сервиса запросов к типам занятия.
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
     * Получение типа занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return тип занятия.
     */
    @Override
    public IdNameDto getLessonTypeById(Long id) {
        LessonTypeEntity entity = lessonTypeRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        return IdNameDto.of(entity.getId(), entity.getName());
    }

    /**
     * Добавление типа занятия.
     *
     * @param lessonType тип занятия.
     */
    @Override
    public void saveLessonType(IdNameDto lessonType) {
        LessonTypeEntity entity = new LessonTypeEntity();
        entity.setName(lessonType.getName());
        lessonTypeRepository.save(entity);
    }

    /**
     * Изменение типа занятия.
     *
     * @param id         идентификатор.
     * @param lessonType тип занятия.
     */
    @Override
    public void updateLessonType(Long id, IdNameDto lessonType) {
        LessonTypeEntity updated = lessonTypeRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        if (Objects.nonNull(lessonType.getName())) {
            updated.setName(lessonType.getName());
        }
        lessonTypeRepository.save(updated);
    }

    /**
     * Удаление типа занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonType(Long id) {
        lessonTypeRepository.deleteById(id);
    }

    /**
     * Получение списка типов занятий.
     *
     * @return список типов занятий.
     */
    @Override
    public List<IdNameDto> findAll() {
        return lessonTypeRepository.findAll().stream()
                .map(it -> IdNameDto.of(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Получение списка типов пар для выбора в выпадающем списке.
     *
     * @return список типов пар в модели {@link IdNameDto}.
     */
    @Override
    public List<IdNameDto> findLessonTypes() {
        return lessonTypeRepository.findAll().stream()
                .map(lessonType -> IdNameDto.of(lessonType.getId(), lessonType.getName()))
                .collect(Collectors.toList());
    }
}
