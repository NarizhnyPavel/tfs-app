package com.TimeForStudy.application.notification.service.impl;

import com.TimeForStudy.application.notification.domain.NotificationRepository;
import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности уведомление
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    /**
     * {@link NotificationRepository}
     */
    private final NotificationRepository notificationRepository;

    /**
     * Возвращение уведомления по идентификатору.
     *
     * @param id идентификатор.
     * @return уведомление.
     */
    @Override
    public NotificationDto getNotificationById(long id) {
        return null;
    }

    /**
     * Сохранение уведомления.
     *
     * @param addNotificationDto уведомление.
     */
    @Override
    public void saveNotification(AddNotificationDto addNotificationDto) {

    }

    /**
     * Изменение значений уведомления.
     *
     * @param id идентификатор.
     * @param addNotificationDto уведомление.
     */
    @Override
    public void updateNotification(long id, AddNotificationDto addNotificationDto) {

    }

    /**
     * Удаление уведомления.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteNotification(long id) {

    }

    /**
     * Возвращение всех существующих уведомлений.
     *
     * @return список уведомлений.
     */
    @Override
    public List<NotificationDto> findAll() {
        return null;
    }
}
