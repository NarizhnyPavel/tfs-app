package com.TimeForStudy.application.notification.service.impl;

import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import com.TimeForStudy.application.notification.domain.NotificationRepository;
import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * {@link LessonRepository}
     */
    private final LessonRepository lessonRepository;

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;


    /**
     * Возвращение уведомления по идентификатору.
     *
     * @param id идентификатор.
     * @return уведомление.
     */
    @Override
    public NotificationDto getNotificationById(long id) {
        NotificationEntity notificationEntity = notificationRepository.findById(id)
                .orElseThrow(ErrorDescription.NOTIFICATION_NOT_FOUNT::exception);
        return NotificationDto.of(notificationEntity);
    }

    /**
     * Сохранение уведомления.
     *
     * @param addNotificationDto уведомление.
     */
    @Override
    public void saveNotification(AddNotificationDto addNotificationDto) {
        lessonRepository.findById(addNotificationDto.getLessons().getId())
                .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
        userRepository.findById(addNotificationDto.getSender().getId())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        NotificationEntity notificationEntity = new NotificationEntity(addNotificationDto);
        notificationRepository.save(notificationEntity);
    }

    /**
     * Изменение значений уведомления.
     *
     * @param id идентификатор.
     * @param addNotificationDto уведомление.
     */
    @Override
    public void updateNotification(long id, AddNotificationDto addNotificationDto) {
        NotificationEntity updated = notificationRepository.findById(id)
                .orElseThrow(ErrorDescription.NOTIFICATION_NOT_FOUNT::exception);
        if (addNotificationDto.getLessons()!=null) {
            lessonRepository.findById(addNotificationDto.getLessons().getId())
                    .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
            updated.setLessons(LessonDto.on(addNotificationDto.getLessons()));
        }
        if (addNotificationDto.getSender()!=null) {
            userRepository.findById(addNotificationDto.getSender().getId())
                    .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
            updated.setSender(UserDto.on(addNotificationDto.getSender()));
        }
            updated.setType(addNotificationDto.isType());
        if (addNotificationDto.getText()!=null) {
            updated.setText(addNotificationDto.getText());
        }
        if (addNotificationDto.getLessonPosition()!=0) {
            updated.setLessonPosition(addNotificationDto.getLessonPosition());
        }
        notificationRepository.save(updated);
    }

    /**
     * Удаление уведомления.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteNotification(long id) {
        notificationRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих уведомлений.
     *
     * @return список уведомлений.
     */
    @Override
    public List<NotificationDto> findAll() {
        List<NotificationEntity> notificationEntities = notificationRepository.findAll();
        return notificationEntities.stream().map(NotificationDto::of).collect(Collectors.toList());
    }
}
