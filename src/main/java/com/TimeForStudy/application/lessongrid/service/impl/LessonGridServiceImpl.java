package com.TimeForStudy.application.lessongrid.service.impl;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.service.impl.LessonServiceImpl;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.lessongrid.service.LessonGridService;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности место занятие в сетке
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonGridServiceImpl implements LessonGridService {

    /**
     * {@link LessonGridRepository}
     */
    private final LessonGridRepository lessonGridRepository;

    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

    /**
     * Возвращение места занятия в сетке по идентификатору.
     *
     * @param id идентификатор.
     * @return место занятия в сетке.
     */
    @Override
    public LessonGridDto getLessonGridById(long id) {
        LessonGridEntity lessonGridEntity = lessonGridRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_GRID_NOT_FOUNT::exception);
        return LessonGridDto.of(lessonGridEntity);
    }

    /**
     * Сохранение места занятия в сетке.
     *
     * @param addLessonGridDto место занятия в сетке.
     */
    @Override
    public void saveLessonGrid(AddLessonGridDto addLessonGridDto) {
        universityRepository.findById(addLessonGridDto.getUniversity().getId())
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        LessonGridEntity lessonGridEntity = new LessonGridEntity(addLessonGridDto);
        lessonGridRepository.save(lessonGridEntity);
    }

    /**
     * Изменение значений места занятия в сетке.
     *
     * @param id идентификатор.
     * @param addLessonGridDto место занятия в сетке.
     */
    @Override
    public void updateLessonGrid(long id, AddLessonGridDto addLessonGridDto) {

        LessonGridEntity updated = lessonGridRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_GRID_NOT_FOUNT::exception);
        if (addLessonGridDto.getLessonNumber()!=0) {
            updated.setLessonNumber(addLessonGridDto.getLessonNumber());
        }
        if (addLessonGridDto.getTime()!=null) {
            updated.setTime(addLessonGridDto.getTime());
        }
        if (addLessonGridDto.getUniversity()!=null) {
            universityRepository.findById(addLessonGridDto.getUniversity().getId())
                    .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
            updated.setUniversity(UniversityDto.on(addLessonGridDto.getUniversity()));
        }
        lessonGridRepository.save(updated);
    }

    /**
     * Удаление места занятия в сетке.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLessonGrid(long id) {
        lessonGridRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих мест занятий в сетке.
     *
     * @return список мест занятий в сетке.
     */
    @Override
    public List<LessonGridDto> findAll() {
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAll();
        return lessonGridEntities.stream().map(LessonGridDto::of).collect(Collectors.toList());
    }

    /**
     * Сортировка позиций.
     */
    class SortByLessonGrid implements Comparator<LessonGridEntity> {
        // Используется для сортировки в порядке возрастания
        // номер

        public int compare(LessonGridEntity a, LessonGridEntity b) {
            return a.getLessonNumber() - b.getLessonNumber();
        }
    }
    /**
     * Возвращение время пар.
     *
     * @return время пар.
     */
    @Override
    public List<UniversitiesDto> findTimes() {
        List<UniversitiesDto> universitiesDtos = new ArrayList<>();
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAll();

        Collections.sort(lessonGridEntities, new LessonGridServiceImpl.SortByLessonGrid());
        for (LessonGridEntity lessonGrid : lessonGridEntities) {
            universitiesDtos.add(new UniversitiesDto(lessonGrid.getLessonNumber(), lessonGrid.getTime()));
        }
        return universitiesDtos;
    }
}
