package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.domain.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель добавления связки группы и занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@AllArgsConstructor
public class AddLessonGroup {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Поле со значением.
     */
    private String label;
    /**
     * Номер.
     */
    private Integer number = 1;

    public static AddLessonGroup of(GroupEntity groupEntity) {
        return new AddLessonGroup(groupEntity.getId(),
                groupEntity.getNumber(), Integer.parseInt(groupEntity.getNumber()));
    }

}
