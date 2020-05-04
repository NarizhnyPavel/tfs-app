package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.*;
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
     * @return учебное заведение.
     */
    @Override
    public AddUniversityAndLessonGridDto getUniversityById() {
        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByUniversity(universityEntity);
        AddUniversityAndLessonGridDto addUniversityAndLessonGridDto = new AddUniversityAndLessonGridDto();
        LessonGridPosition lessonGridPosition = new LessonGridPosition();
        for (LessonGridEntity lessonGrid : lessonGridEntities) {
            if (lessonGrid.getLessonNumber()==1) {
                lessonGridPosition.setPosition1(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==2) {
                lessonGridPosition.setPosition2(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==3) {
                lessonGridPosition.setPosition3(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==4) {
                lessonGridPosition.setPosition4(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==5) {
                lessonGridPosition.setPosition5(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==6) {
                lessonGridPosition.setPosition6(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber()==7) {
                lessonGridPosition.setPosition7(lessonGrid.getTime());
            }
        }

        addUniversityAndLessonGridDto.setLessonGridPosition(lessonGridPosition);
        addUniversityAndLessonGridDto.setColor1(universityEntity.getColor1());
        addUniversityAndLessonGridDto.setColor2(universityEntity.getColor2());
        addUniversityAndLessonGridDto.setColor3(universityEntity.getColor3());
        addUniversityAndLessonGridDto.setLessonDuration(universityEntity.getLessonDuration());
        addUniversityAndLessonGridDto.setLogo(universityEntity.getLogotype());
        addUniversityAndLessonGridDto.setName(universityEntity.getName());
        addUniversityAndLessonGridDto.setWeeks(universityEntity.getWeeks());
        Week workday = new Week();
        if (universityEntity.getWorkDays().indexOf('1') != -1) {
            workday.setMonday(true);
        }
        if (universityEntity.getWorkDays().indexOf('2') != -1) {
            workday.setTuesday(true);
        }
        if (universityEntity.getWorkDays().indexOf('3') != -1) {
            workday.setWednesday(true);
        }
        if (universityEntity.getWorkDays().indexOf('4') != -1) {
            workday.setThursday(true);
        }
        if (universityEntity.getWorkDays().indexOf('5') != -1) {
            workday.setFriday(true);
        }
        if (universityEntity.getWorkDays().indexOf('6') != -1) {
            workday.setSaturday(true);
        }
        if (universityEntity.getWorkDays().indexOf('7') != -1) {
            workday.setSunday(true);
        }
        addUniversityAndLessonGridDto.setWorkDays(workday);
        return addUniversityAndLessonGridDto;
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
     * @param addUniversityAndLessonGridDto учебное заведение.
     */
    @Override
    public void updateUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        UniversityEntity updated = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (addUniversityAndLessonGridDto.getName() != null) {
            updated.setName(addUniversityAndLessonGridDto.getName());
        }
        if (addUniversityAndLessonGridDto.getLessonDuration() != 0) {
            updated.setLessonDuration(addUniversityAndLessonGridDto.getLessonDuration());
        }
        if (addUniversityAndLessonGridDto.getWeeks() != 0) {
            updated.setWeeks(addUniversityAndLessonGridDto.getWeeks());
        }
        if (addUniversityAndLessonGridDto.getWorkDays() != null) {
            String workday = "";
            if (addUniversityAndLessonGridDto.getWorkDays().isMonday()) {
                workday+='1';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isTuesday()) {
                workday+='2';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isWednesday()) {
                workday+='3';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isThursday()) {
                workday+='4';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isFriday()) {
                workday+='5';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isSaturday()) {
                workday+='6';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isSunday()) {
                workday+='7';
            }
            updated.setWorkDays(workday);
        }
        if (addUniversityAndLessonGridDto.getColor1() != null) {
            updated.setColor1(addUniversityAndLessonGridDto.getColor1());
        }
        if (addUniversityAndLessonGridDto.getColor2() != null) {
            updated.setColor2(addUniversityAndLessonGridDto.getColor2());
        }
        if (addUniversityAndLessonGridDto.getColor3() != null) {
            updated.setColor3(addUniversityAndLessonGridDto.getColor3());
        }
        if (addUniversityAndLessonGridDto.getLogo() != null) {
            updated.setLogotype(addUniversityAndLessonGridDto.getLogo());
        }
        universityRepository.save(updated);

        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(1);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(2);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(3);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(4);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(5);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(6);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7() != null) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(7);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7());
            lessonGridRepository.save(lessonGridEntity);
        }
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
