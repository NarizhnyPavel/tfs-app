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
    private List<PositionDto> position;

    /**
     *  Кабинет, в котором проходит занятие.
     */
    private long classroom;

    /**
     * Предмет преподаваемый на занятии.
     */
    private long subject;

    /**
     * Преподаватель, который проводит занятие.
     */
    private long professor;

    /**
     * Тип занятия.
     */
    private long lessonType;

    /**
     * Список групп.
     */
    private List<GroupDto> groups;
}
