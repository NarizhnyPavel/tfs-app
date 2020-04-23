package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * {@link LessonGridRepository}
     */
    private final LessonGridRepository lessonGridRepository;

    /**
     * Возвращение учебного заведения по идентификатору.
     *
     * @param id идентификатор.
     * @return учебное заведение.
     */
    @Override
    public UniversityDto getUniversityById(long id) {
        UniversityEntity universityEntity = universityRepository.findById(id)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        return UniversityDto.of(universityEntity);
    }

    /**
     * Сохранение учебного заведения.
     *
     * @param addUniversityAndLessonGridDto учебное заведение.
     */
    @Override
    public void saveUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        AddUniversityDto addUniversityDto = new AddUniversityDto(addUniversityAndLessonGridDto);
        UniversityEntity universityEntity = new UniversityEntity(addUniversityDto);
        universityRepository.save(universityEntity);
        LessonGridEntity lessonGridEntity1 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1(), 1, universityEntity);
        LessonGridEntity lessonGridEntity2 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2(), 2, universityEntity);
        LessonGridEntity lessonGridEntity3 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3(), 3, universityEntity);
        LessonGridEntity lessonGridEntity4 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4(), 4, universityEntity);
        LessonGridEntity lessonGridEntity5 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5(), 5, universityEntity);
        LessonGridEntity lessonGridEntity6 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6(), 6, universityEntity);
        LessonGridEntity lessonGridEntity7 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7(), 7, universityEntity);
        if (lessonGridEntity1!=null)
            lessonGridRepository.save(lessonGridEntity1);
        if (lessonGridEntity2!=null)
            lessonGridRepository.save(lessonGridEntity2);
        if (lessonGridEntity3!=null)
            lessonGridRepository.save(lessonGridEntity3);
        if (lessonGridEntity4!=null)
            lessonGridRepository.save(lessonGridEntity4);
        if (lessonGridEntity5!=null)
            lessonGridRepository.save(lessonGridEntity5);
        if (lessonGridEntity6!=null)
            lessonGridRepository.save(lessonGridEntity6);
        if (lessonGridEntity7!=null)
            lessonGridRepository.save(lessonGridEntity7);
    }

    /**
     * Изменение значений учебного заведения.
     *
     * @param id идентификатор.
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void updateUniversity(long id, AddUniversityDto addUniversityDto) {
        UniversityEntity updated = universityRepository.findById(id)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (addUniversityDto.getName()!=null) {
            updated.setName(addUniversityDto.getName());
        }
        if (addUniversityDto.getLessonDuration()!=0) {
            updated.setLessonDuration(addUniversityDto.getLessonDuration());
        }
        if (addUniversityDto.getWeeks()!=0) {
            updated.setWeeks(addUniversityDto.getWeeks());
        }
        if (addUniversityDto.getWorkDays()!=null) {
            updated.setWorkDays(addUniversityDto.getWorkDays());
        }
        if (addUniversityDto.getColor()!=null) {
            updated.setColor(addUniversityDto.getColor());
        }
        if (addUniversityDto.getLogotype()!=null) {
            updated.setLogotype(addUniversityDto.getLogotype());
        }
        //TODO доделать с lessonGrid
        universityRepository.save(updated);
    }

    /**
     * Удаление учебного заведения.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUniversity(long id) {
        universityRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих учебных заведений.
     *
     * @return список учебных заведений.
     */
    @Override
    public List<UniversityDto> findAll() {
        List<UniversityEntity> universityEntities = universityRepository.findAll();
        return universityEntities.stream().map(UniversityDto::of).collect(Collectors.toList());
    }
}
