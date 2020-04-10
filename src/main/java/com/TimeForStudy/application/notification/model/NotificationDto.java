package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.notification.domain.NotificationEntity;
import lombok.Data;


/**
 * Модель представления сущности уведомление
 *
 * @author Velikanov Artyom
 */
@Data
public class NotificationDto {

    private long id;

    /**
     * Позиция занятия
     */
    private int lessonPosition;

    /**
     * Текст уведомления
     */
    private String text;

    public static NotificationDto of(NotificationEntity notificationEntity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notificationEntity.getId());
        dto.setLessonPosition(notificationEntity.getLessonPosition());
        dto.setText(notificationEntity.getText());
        return dto;
    }

    public static NotificationEntity on(NotificationDto notificationDto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(notificationDto.getId());
        entity.setLessonPosition(notificationDto.getLessonPosition());
        entity.setText(notificationDto.getText());
        return entity;
    }
}
