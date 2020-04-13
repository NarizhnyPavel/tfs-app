package com.TimeForStudy.application.lessonposition.service.impl;

import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionRepository;
import com.TimeForStudy.application.lessonposition.model.AddLessonPositionDto;
import com.TimeForStudy.application.lessonposition.model.LessonPositionDto;
import com.TimeForStudy.application.lessonposition.service.LessonPositionService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonPositionServiceImpl implements LessonPositionService {

    /**
     * {@link LessonRepository}
     */
    private final LessonRepository lessonRepository;

    /**
     * {@link LessonPositionRepository}
     */
    private final LessonPositionRepository lessonPositionRepository;

    /**
     * Возвращение позиции лекции по идентификатору.
     *
     * @param id идентификатор.
     * @return позиция лекции.
     */
    @Override
    public LessonPositionDto getLessonPositionById(long id) {
        LessonPositionEntity lessonPositionEntity = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        return LessonPositionDto.of(lessonPositionEntity);
    }

    /**
     * Сохранение позиции лекции.
     *
     * @param addLessonPositionDto позиция лекции
     */
    @Override
    public void saveLessonPosition(AddLessonPositionDto addLessonPositionDto) {
        lessonRepository.findById(addLessonPositionDto.getLesson().getId())
                .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
        LessonPositionEntity lessonPositionEntity = new LessonPositionEntity(addLessonPositionDto);
        lessonPositionRepository.save(lessonPositionEntity);
    }

    /**
     * Изменение значений позиции лекции.
     *
     * @param id идентификатор.
     * @param addLessonPositionDto позиция лекции.
     */
    @Override
    public void updateLessonPosition(long id, AddLessonPositionDto addLessonPositionDto) {
        LessonPositionEntity updated = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        if (addLessonPositionDto.getLesson()!=null) {
            lessonRepository.findById(addLessonPositionDto.getLesson().getId())
                    .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
            updated.setLesson(LessonDto.on(addLessonPositionDto.getLesson()));
        }
    }

    /**
     * Удаление позиции лекции.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonPosition(long id) {
        lessonPositionRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих позиций лекции.
     *
     * @return список позиций лекции.
     */
    @Override
    public List<LessonPositionDto> findAll() {
        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository.findAll();
        return lessonPositionEntities.stream().map(LessonPositionDto::of).collect(Collectors.toList());
    }
}
