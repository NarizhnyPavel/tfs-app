package com.TimeForStudy.application.university.model;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import lombok.Data;

/**
 * Модель представления сущности учебного заведения.
 *
 * @author Velikanov Artyom
 */
@Data
public class UniversityDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Наименование университета.
     */
    private String name;
    /**
     * Количество деления недель.
     */
    private Integer weeks;
    /**
     * Количество учебных дней.
     */
    private Week workDays;
    /**
     * Цвет №1.
     */
    private String color1;
    /**
     * Цвет №2.
     */
    private String color2;
    /**
     * Цвет №3.
     */
    private String color3;
    /**
     * Логотип.
     */
    private String logotype;
    /**
     * Длительность одного занятия.
     */
    private Integer lessonDuration;

    public  static  UniversityDto of(UniversityEntity universityEntity) {
        UniversityDto dto = new UniversityDto();
        dto.setId(universityEntity.getId());
        dto.setName(universityEntity.getName());
        dto.setWeeks(universityEntity.getWeeks());
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
        dto.setWorkDays(workday);
        dto.setColor1(universityEntity.getColor1());
        dto.setColor2(universityEntity.getColor2());
        dto.setColor3(universityEntity.getColor3());
        dto.setLogotype(universityEntity.getLogotype());
        dto.setLessonDuration(universityEntity.getLessonDuration());
        return dto;
    }

    public static UniversityEntity convertUniversity(UniversityDto universityDto) {
        UniversityEntity entity = new UniversityEntity();
        entity.setId(universityDto.getId());
        entity.setName(universityDto.getName());
        entity.setWeeks(universityDto.getWeeks());
        String workday = "";
        if (universityDto.getWorkDays().isMonday()) {
            workday+='1';
        }
        if (universityDto.getWorkDays().isTuesday()) {
            workday+='2';
        }
        if (universityDto.getWorkDays().isWednesday()) {
            workday+='3';
        }
        if (universityDto.getWorkDays().isThursday()) {
            workday+='4';
        }
        if (universityDto.getWorkDays().isFriday()) {
            workday+='5';
        }
        if (universityDto.getWorkDays().isSaturday()) {
            workday+='6';
        }
        if (universityDto.getWorkDays().isSunday()) {
            workday+='7';
        }
        entity.setWorkDays(workday);
        entity.setColor1(universityDto.getColor1());
        entity.setColor2(universityDto.getColor2());
        entity.setColor3(universityDto.getColor3());
        entity.setLogotype(universityDto.getLogotype());
        entity.setLessonDuration(universityDto.getLessonDuration());
        return entity;
    }
}
