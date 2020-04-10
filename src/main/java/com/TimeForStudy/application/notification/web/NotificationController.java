package com.TimeForStudy.application.notification.web;

import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "/notification")
    public List<NotificationDto> getNotifications() {
        return notificationService.findAll();
    }

    /**
     * Возвращает уведомление по идентификатору.
     *
     * @param id идентификатор.
     * @return уведомление
     */
    @GetMapping(value = "/notification/{id}")
    public NotificationDto getNotification(@PathVariable long id) {
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

    /**
     * Изменяет данное уведомление.
     *
     * @param id идентификатор.
     * @param addNotificationDto уведомление.
     */
    @PutMapping(value = "/notification/update/{id}")
    public void updateNotification(@PathVariable long id, @RequestBody AddNotificationDto addNotificationDto) {
        notificationService.updateNotification(id, addNotificationDto);
    }

    /**
     * Удаляет уведомление.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/notification/delete/{id}")
    public void deleteNotification(@PathVariable long id) {
        notificationService.deleteNotification(id);
    }
}
