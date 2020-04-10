package com.TimeForStudy.application.notification.service;

import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности уведомление
 *
 * @author Velikanov Artyom
 */
public interface NotificationService {

    /**
     * Возвращение уведомления по идентификатору.
     *
     * @param id идентификатор.
     * @return уведомление.
     */
    NotificationDto getNotificationById(long id);

    /**
     * Сохранение уведомления.
     *
     * @param addNotificationDto уведомление.
     */
    void saveNotification(AddNotificationDto addNotificationDto);

    /**
     * Изменение значений уведомления.
     *
     * @param id идентификатор.
     * @param addNotificationDto уведомление.
     */
    void updateNotification(long id, AddNotificationDto addNotificationDto);

    /**
     * Удаление уведомления.
     *
     * @param id идентификатор.
     */
    void deleteNotification(long id);

    /**
     * Возвращение всех существующих уведомлений.
     *
     * @return список уведомлений.
     */
    List<NotificationDto> findAll();
}
