package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.model.GroupsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель добавления занятия.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class NewLessonDto {

    /**
     *  Позиция.
     */
    private List<PositionDto> position;
    /**
     *  Кабинет, в котором проходит занятие.
     */
    private Long classroom;
    /**
     * Предмет преподаваемый на занятии.
     */
    private Long subject;
    /**
     * Преподаватель, который проводит занятие.
     */
    private Long professor;
    /**
     * Тип занятия.
     */
    private Long lessonType;
    /**
     * Список групп.
     */
    private List<GroupsDto> groups;
}
