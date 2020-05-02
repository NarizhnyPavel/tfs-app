package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversitiesDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1() != null) {
            LessonGridEntity lessonGridEntity1 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1(), 1, universityEntity);
            lessonGridRepository.save(lessonGridEntity1);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2() != null) {
            LessonGridEntity lessonGridEntity2 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2(), 2, universityEntity);
            lessonGridRepository.save(lessonGridEntity2);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3() != null) {
            LessonGridEntity lessonGridEntity3 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3(), 3, universityEntity);
            lessonGridRepository.save(lessonGridEntity3);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4() != null) {
            LessonGridEntity lessonGridEntity4 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4(), 4, universityEntity);
            lessonGridRepository.save(lessonGridEntity4);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5() != null) {
            LessonGridEntity lessonGridEntity5 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5(), 5, universityEntity);
            lessonGridRepository.save(lessonGridEntity5);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6() != null) {
            LessonGridEntity lessonGridEntity6 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6(), 6, universityEntity);
            lessonGridRepository.save(lessonGridEntity6);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7() != null) {
            LessonGridEntity lessonGridEntity7 = new LessonGridEntity(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7(), 7, universityEntity);
            lessonGridRepository.save(lessonGridEntity7);
        }
    }

    /**
     * Изменение значений учебного заведения.
     *
     * @param id               идентификатор.
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void updateUniversity(long id, AddUniversityDto addUniversityDto) {
        UniversityEntity updated = universityRepository.findById(id)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (addUniversityDto.getName() != null) {
            updated.setName(addUniversityDto.getName());
        }
        if (addUniversityDto.getLessonDuration() != 0) {
            updated.setLessonDuration(addUniversityDto.getLessonDuration());
        }
        if (addUniversityDto.getWeeks() != 0) {
            updated.setWeeks(addUniversityDto.getWeeks());
        }
        if (addUniversityDto.getWorkDays() != null) {
            updated.setWorkDays(addUniversityDto.getWorkDays());
        }
        if (addUniversityDto.getColor() != null) {
            updated.setColor(addUniversityDto.getColor());
        }
        if (addUniversityDto.getLogo() != null) {
            updated.setLogotype(addUniversityDto.getLogo());
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

    /**
     * Возвращение дни недели.
     *
     * @return дни недели.
     */
    @Override
    public List<UniversitiesDto> findWorkDays() {
        List<UniversitiesDto> universitiesDtos = new ArrayList<>();
        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (universityEntity.getWorkDays().indexOf('1') != -1) {
            universitiesDtos.add(new UniversitiesDto(1, "Понедельник"));
        }
        if (universityEntity.getWorkDays().indexOf('2') != -1) {
            universitiesDtos.add(new UniversitiesDto(2, "Вторник"));
        }
        if (universityEntity.getWorkDays().indexOf('3') != -1) {
            universitiesDtos.add(new UniversitiesDto(3, "Среда"));
        }
        if (universityEntity.getWorkDays().indexOf('4') != -1) {
            universitiesDtos.add(new UniversitiesDto(4, "Четверг"));
        }
        if (universityEntity.getWorkDays().indexOf('5') != -1) {
            universitiesDtos.add(new UniversitiesDto(5, "Пятница"));
        }
        if (universityEntity.getWorkDays().indexOf('6') != -1) {
            universitiesDtos.add(new UniversitiesDto(6, "Суббота"));
        }
        if (universityEntity.getWorkDays().indexOf('7') != -1) {
            universitiesDtos.add(new UniversitiesDto(7, "Воскресенье"));
        }
        return universitiesDtos;
    }
}
