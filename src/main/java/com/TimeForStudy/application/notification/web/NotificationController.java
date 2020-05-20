package com.TimeForStudy.application.notification.web;

import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.model.NotificationStringDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * Возвращает список уведомлений.
     *
     * @return список уведомлений.
     */
    @GetMapping(value = "/notifications")
    public List<NotificationDto> getNotifications() {
        return notificationService.findAll();
    }

    /**
     * Возвращает уведомление по идентификатору пользователя.
     *
     * @param id идентификатор.
     * @return уведомление
     */
    @GetMapping(value = "/notification/{id}")
    public List<NotificationStringDto> getNotification(@PathVariable long id) {
        return notificationService.getNotificationById(id);
    }

    /**
     * Добавляет новое уведомление.
     *
     * @param addNotificationDto уведомление.
     */
    @PostMapping(value = "/notification/add")
    public void addNotification(@RequestBody AddNotificationDto addNotificationDto) {
        notificationService.saveNotification(addNotificationDto);
    }



    }
