package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


/**
 * Модель для добавления сущности уведомление
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddNotificationDto {

    /**
     * Позиция занятия
     */
    private int lessonPosition;

    /**
     * Текст уведомления
     */
    private String text;

    /**
     * Тип уведомления (false - уведомление; true - запрос)
     */
    private boolean type;

    /**
     * Лекция, к которой относится данное уведомление
     */
    private LessonDto lessons;

    /**
     * Отправитель уведомления
     */
    private UserDto sender;

    /**
     * Получатели уведомления
     */
    private GroupDto group;
}
