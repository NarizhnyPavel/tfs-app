package com.TimeForStudy.application.university.model;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import lombok.Data;

import javax.persistence.Column;

/**
 * Модель представления сущности учебного заведения
 *
 * @author Velikanov Artyom
 */
@Data
public class UniversityDto {

    private long id;

    /**
     * Наименование университета
     */
    private String name;

    /**
     * Количество деления недель
     */
    private int weeks;

    /**
     * Количество учебных дней
     */
    private String workDays;

    /**
     * Длительность одного занятия
     */
    private int lessonDuration;

    public  static  UniversityDto of(UniversityEntity universityEntity) {
        UniversityDto dto = new UniversityDto();
        dto.setId(universityEntity.getId());
        dto.setName(universityEntity.getName());
        dto.setWeeks(universityEntity.getWeeks());
        dto.setWorkDays(universityEntity.getWorkDays());
        dto.setLessonDuration(universityEntity.getLessonDuration());
        return dto;
    }

    public static UniversityEntity on(UniversityDto universityDto) {
        UniversityEntity entity = new UniversityEntity();
        entity.setId(universityDto.getId());
        entity.setName(universityDto.getName());
        entity.setWeeks(universityDto.getWeeks());
        entity.setWorkDays(universityDto.getWorkDays());
        entity.setLessonDuration(universityDto.getLessonDuration());
        return entity;
    }
}
