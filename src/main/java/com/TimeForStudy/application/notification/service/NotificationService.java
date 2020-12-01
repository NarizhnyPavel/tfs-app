package com.TimeForStudy.application.notification.service;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.notification.model.NotificationDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к уведомлениям.
 *
 * @author Velikanov Artyom
 */
public interface NotificationService {

    /**
     * Получение уведомлений для текущего пользователя.
     *
     * @return уведомления.
     */
    List<NotificationDto> getNotifications();

    /**
     * Удаление уведомления.
     *
     * @param id идентификатор.
     */
    void deleteNotification(LessonPositionEntity id);

}
