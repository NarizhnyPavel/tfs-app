package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.notification.domain.NotificationEntity;
import lombok.Data;

import javax.persistence.Column;


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

    /**
     * Тип уведомления (false - уведомление; true - запрос)
     */
    @Column(name = "type")
    private boolean type;

    public static NotificationDto of(NotificationEntity notificationEntity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notificationEntity.getId());
        dto.setLessonPosition(notificationEntity.getLessonPosition());
        dto.setText(notificationEntity.getText());
        dto.setType(notificationEntity.isType());
        return dto;
    }

    public static NotificationEntity on(NotificationDto notificationDto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(notificationDto.getId());
        entity.setLessonPosition(notificationDto.getLessonPosition());
        entity.setText(notificationDto.getText());
        entity.setType(notificationDto.isType());
        return entity;
    }
}
