package com.TimeForStudy.application.subject.model;

import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.AddUserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Модель для добавления сущности преподаваемая дисциплина
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddSubjectDto {

    /**
     * Наименование преподаваемой дисциплины
     */
    private String name;

    /**
     * Сокращение
     */
    private String arc;

    public AddSubjectDto(String name, String arc) {
        this.arc = arc;
        this.name = name;
    }

    public static SubjectEntity on(AddSubjectDto addSubjectDto) {
        SubjectEntity entity = new SubjectEntity();
        entity.setName(addSubjectDto.getName());
        entity.setArc(addSubjectDto.getArc());
        return  entity;
    }
}
