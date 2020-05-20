package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.notification.domain.NotificationEntity;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;


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
    private String lessonPosition;

    /**
     * Текст уведомления
     */
    private LocalDate date;

    /**
     * Тип уведомления (false - уведомление; true - запрос)
     */
    private boolean type;

    public static NotificationDto of(NotificationEntity notificationEntity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notificationEntity.getId());
        dto.setLessonPosition(notificationEntity.getLessonPosition());
        dto.setDate(notificationEntity.getDate());
        dto.setType(notificationEntity.isType());
        return dto;
    }

    public static NotificationEntity on(NotificationDto notificationDto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(notificationDto.getId());
        entity.setLessonPosition(notificationDto.getLessonPosition());
        entity.setDate(notificationDto.getDate());
        entity.setType(notificationDto.isType());
        return entity;
    }
}
