package com.TimeForStudy.application.notification.web;

import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Обработчик запросов уведомлений.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class NotificationController {

    /**
     * {@link NotificationService}
     */
    private final NotificationService notificationService;

    /**
     * Получение уведомлений для текущего пользователя.
     *
     * @return уведомления.
     */
    @GetMapping(value = "/notification")
    public List<NotificationDto> getNotification() {
        return notificationService.getNotifications();
    }

}
