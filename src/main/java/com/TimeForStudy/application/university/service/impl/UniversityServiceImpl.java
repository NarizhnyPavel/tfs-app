package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityAndLessonGridDto;
import com.TimeForStudy.application.university.model.LessonGridPosition;
import com.TimeForStudy.application.university.model.Week;
import com.TimeForStudy.application.university.service.UniversityService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Реализация сервиса запросов к учебным заведениям.
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
     * Получение информации об учебном заведении.
     *
     * @return UniversityDto информация об учебном заведении.
     */
    @Override
    public AddUniversityAndLessonGridDto getUniversity() {
        UniversityEntity universityEntity = universityRepository.findById(1L)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByUniversity(universityEntity);
        AddUniversityAndLessonGridDto addUniversityAndLessonGridDto = new AddUniversityAndLessonGridDto();
        LessonGridPosition lessonGridPosition = new LessonGridPosition();
        lessonGridEntities.forEach(lessonGrid -> {
            if (lessonGrid.getLessonNumber().equals(1)) {
                lessonGridPosition.setPosition1(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(2)) {
                lessonGridPosition.setPosition2(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(3)) {
                lessonGridPosition.setPosition3(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(4)) {
                lessonGridPosition.setPosition4(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(5)) {
                lessonGridPosition.setPosition5(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(6)) {
                lessonGridPosition.setPosition6(lessonGrid.getTime());
            }
            if (lessonGrid.getLessonNumber().equals(7)) {
                lessonGridPosition.setPosition7(lessonGrid.getTime());
            }
        });
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
     * Редактирование данных об учебном заведении.
     *
     * @param addUniversityAndLessonGridDto информация об университете.
     * @return статус редактирования.
     */
    @Override
    public void updateUniversity(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        UniversityEntity updated = universityRepository.findById(1L)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getName())) {
            updated.setName(addUniversityAndLessonGridDto.getName());
        }
        if (addUniversityAndLessonGridDto.getLessonDuration().equals(0)) {
            updated.setLessonDuration(addUniversityAndLessonGridDto.getLessonDuration());
        }
        if (addUniversityAndLessonGridDto.getWeeks().equals(0)) {
            updated.setWeeks(addUniversityAndLessonGridDto.getWeeks());
        }
        if (Objects.nonNull(addUniversityAndLessonGridDto.getWorkDays())) {
            String workday = "";
            if (addUniversityAndLessonGridDto.getWorkDays().isMonday()) {
                workday += '1';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isTuesday()) {
                workday += '2';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isWednesday()) {
                workday += '3';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isThursday()) {
                workday += '4';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isFriday()) {
                workday += '5';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isSaturday()) {
                workday += '6';
            }
            if (addUniversityAndLessonGridDto.getWorkDays().isSunday()) {
                workday += '7';
            }
            updated.setWorkDays(workday);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getColor1())) {
            updated.setColor1(addUniversityAndLessonGridDto.getColor1());
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getColor2())) {
            updated.setColor2(addUniversityAndLessonGridDto.getColor2());
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getColor3())) {
            updated.setColor3(addUniversityAndLessonGridDto.getColor3());
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLogo())) {
            updated.setLogotype(addUniversityAndLessonGridDto.getLogo());
        }
        universityRepository.save(updated);

        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(1);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition1());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(2);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition2());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(3);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition3());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(4);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition4());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(5);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition5());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(6);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition6());
            lessonGridRepository.save(lessonGridEntity);
        }
        if (StringUtils.isNotBlank(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7())) {
            List<LessonGridEntity> lessonGridEntities = lessonGridRepository.findAllByLessonNumber(7);
            LessonGridEntity lessonGridEntity = lessonGridEntities.get(0);
            lessonGridEntity.setTime(addUniversityAndLessonGridDto.getLessonGridPosition().getPosition7());
            lessonGridRepository.save(lessonGridEntity);
        }
    }

    /**
     * Получение списка рабочих дней недели.
     *
     * @return список преподавателей.
     */
    @Override
    public List<IdNameDto> findWorkDays() {
        List<IdNameDto> lists = new ArrayList<>();
        UniversityEntity universityEntity = universityRepository.findById(1L)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (universityEntity.getWorkDays().indexOf('1') != -1) {
            lists.add(IdNameDto.of(1L, "Понедельник"));
        }
        if (universityEntity.getWorkDays().indexOf('2') != -1) {
            lists.add(IdNameDto.of(2L, "Вторник"));
        }
        if (universityEntity.getWorkDays().indexOf('3') != -1) {
            lists.add(IdNameDto.of(3L, "Среда"));
        }
        if (universityEntity.getWorkDays().indexOf('4') != -1) {
            lists.add(IdNameDto.of(4L, "Четверг"));
        }
        if (universityEntity.getWorkDays().indexOf('5') != -1) {
            lists.add(IdNameDto.of(5L, "Пятница"));
        }
        if (universityEntity.getWorkDays().indexOf('6') != -1) {
            lists.add(IdNameDto.of(6L, "Суббота"));
        }
        if (universityEntity.getWorkDays().indexOf('7') != -1) {
            lists.add(IdNameDto.of(7L, "Воскресенье"));
        }
        return lists;
    }

    /**
     * Получение количества недель.
     *
     * @return количество недель.
     */
    @Override
    public Integer getWeeksNumber() {
        UniversityEntity universityEntity = universityRepository.findById(1L).orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        return universityEntity.getWeeks();
    }
}
