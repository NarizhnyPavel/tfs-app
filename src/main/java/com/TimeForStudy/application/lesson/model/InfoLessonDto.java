package com.TimeForStudy.application.lesson.model;


import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.service.LessonService;
import com.TimeForStudy.application.lesson.service.impl.LessonServiceImpl;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
public class InfoLessonDto {

    private long id;

    /**
     *  Время.
     */
    private String time;

    /**
     *  Кабинет, в котором проходит занятие
     */
    private int classroom;

    /**
     * Предмет преподаваемый на занятии
     */
    private String subject;

    /**
     * Сокращение
     */
    private String arc;

    /**
     * Статус
     */
    private boolean status;

    /**
     * Преподаватель, который проводит занятие
     */
    private String professor;

    /**
     * Тип занятия
     */
    private String lessonType;

    /**
     * Группы
     */
    private String group;

    /**
     * Преподаватель
     */
    private long professorId;

    /*
        если занятие отменено то LessonPositionRepository перадаст в качестве аргумента status = false
     */
    public InfoLessonDto(long id, String time, int classroom, String subject, String arc, Long status, String professor, String lessonType, Long idLessonEntity, long professorId) {
        this.id = id;
        this.time = time;
        this.classroom = classroom;
        this.subject = subject;
        this.arc = arc;
        this.status = status == null ;
        this.professor = professor;
        this.lessonType = lessonType;
        this.group = idLessonEntity.toString();
        this.professorId = professorId;
    }

}
