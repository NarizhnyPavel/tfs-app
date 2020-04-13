package com.TimeForStudy.application.subject.model;

import com.TimeForStudy.application.subject.domain.SubjectEntity;
import lombok.Data;


/**
 * Модель представления сущности преподаваемой дисциплины
 *
 * @author Velikanov Artyom
 */
@Data
public class SubjectDto {

    private long id;

    /**
     * Наименование преподаваемой дисциплины
     */
    private String name;

    public static SubjectDto of(SubjectEntity subjectEntity) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subjectEntity.getId());
        dto.setName(subjectEntity.getName());
        return dto;
    }
    public static SubjectEntity on(SubjectDto subjectDto) {
        SubjectEntity entity = new SubjectEntity();
        entity.setId(subjectDto.getId());
        entity.setName(subjectDto.getName());
        return entity;
    }
}