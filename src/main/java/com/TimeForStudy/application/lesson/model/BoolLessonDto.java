package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoolLessonDto {

    /**
     *  Кабинет, в котором проходит занятие
     */
    private boolean classroom;

    /**
     * Предмет преподаваемый на занятии
     */
    private boolean subject;

    /**
     * Преподаватель, который проводит занятие
     */
    private boolean professor;

    /**
     * Список Групп.
     */
    private List<Boolean> groups;

    public BoolLessonDto() {
        this.classroom = true;
        this.subject = true;
        this.professor = true;
    }
}
