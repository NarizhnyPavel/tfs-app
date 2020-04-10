package com.TimeForStudy.application.semester.model;

import com.TimeForStudy.application.semester.domain.SemesterEntity;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Модель представления сущности семестр
 *
 * @author Velikanov Artyom
 */
@Data
public class SemesterDto {

    private long id;

    /**
     * Дата начала
     */
    @Column(name = "start")
    private LocalDate start;

    /**
     * Дата окончания
     */
    @Column(name = "end")
    private LocalDate end;

    public static SemesterDto of(SemesterEntity semesterEntity) {
        SemesterDto dto = new SemesterDto();
        dto.setId(semesterEntity.getId());
        dto.setStart(semesterEntity.getStart());
        dto.setEnd(semesterEntity.getEnd());
        return dto;
    }

    public static SemesterEntity on(SemesterDto semesterDto) {
        SemesterEntity entity = new SemesterEntity();
        entity.setId(semesterDto.getId());
        entity.setStart(semesterDto.getStart());
        entity.setEnd(semesterDto.getEnd());
        return entity;
    }
}
