package com.TimeForStudy.application.lesson.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель информации о занятии.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor(staticName = "of")
public class InfoLessonDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     *  Время.
     */
    private String time;
    /**
     *  Кабинет, в котором проходит занятие.
     */
    private String classroom;
    /**
     * Предмет преподаваемый на занятии.
     */
    private String subject;
    /**
     * Сокращение.
     */
    private String arc;
    /**
     * Статус.
     */
    private boolean status;
    /**
     * Преподаватель, который проводит занятие.
     */
    private String professor;
    /**
     * Тип занятия.
     */
    private String lessonType;
    /**
     * Группы.
     */
    private String group;
    /**
     * Преподаватель.
     */
    private long professorId;

    /**
     * Если занятие отменено то LessonPositionRepository перадаст в качестве аргумента status = false.
     */
    public InfoLessonDto(Long id, String time, String classroom, String subject, String arc, Long status, String professor, String lessonType, Long idLessonEntity, Long professorId) {
        this.id = id;
        this.time = time;
        this.classroom = classroom;
        this.subject = subject;
        this.arc = arc;
        this.status = status == null;
        this.professor = professor;
        this.lessonType = lessonType;
        this.group = idLessonEntity.toString();
        this.professorId = professorId;
    }

}
