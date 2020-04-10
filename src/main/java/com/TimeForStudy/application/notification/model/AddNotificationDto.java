package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;


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
     * Лекция, к которой относится данное уведомление
     */
    private LessonDto lessons;

    /**
     * Отправитель уведомления
     */
    private UserDto sender;

    /**
     * Получатель уведомления
     */
    private UserDto receiver;
}
