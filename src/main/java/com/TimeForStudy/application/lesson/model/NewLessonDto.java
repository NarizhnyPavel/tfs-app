package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.model.GroupDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель добавления лекции.
 *
 * @author Velikanov Artyom.
 */
@Data
@RequiredArgsConstructor
public class NewLessonDto {

    /**
     *  Позиция.
     */
    private Integer position;

    /**
     *  Кабинет, в котором проходит занятие.
     */
    private int classroom;

    /**
     * Предмет преподаваемый на занятии.
     */
    private String subject;

    /**
     * Преподаватель, который проводит занятие.
     */
    private String professor;

    /**
     * Тип занятия.
     */
    private String lessonType;

    /**
     * Список групп.
     */
    private List<GroupDto> groups;
}
