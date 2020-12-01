package com.TimeForStudy.application.notification.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import com.TimeForStudy.application.notification.domain.NotificationRepository;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.error.ErrorDescription;
import com.TimeForStudy.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса запросов к уведомлениям.
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
     * {@link LessonGridRepository}
     */
    private final LessonGridRepository lessonGridRepository;
    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;
    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;
    /**
     * {@link UserUtils}
     */
    private final UserUtils userUtils;

    /**
     * Получение уведомлений для текущего пользователя.
     *
     * @return уведомления.
     */
    @Override
    public List<NotificationDto> getNotifications() {
        User user = userUtils.getCurrentUser();
        UniversityEntity universityEntity = universityRepository.findById(1L)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        List<NotificationDto> notificationDtos = new ArrayList<>();
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                findAllByUniversity(universityEntity);
        for (GroupEntity groupEntity : user.getGroups()) {
            for (LessonEntity lessonEntity : groupEntity.getLessons()) {
                for (LessonPositionEntity lessonPositionEntity : lessonEntity.getLessonPositions()) {
                    for (NotificationEntity notificationEntity : lessonPositionEntity.getNotificationEntities()) {
                        notificationDtos.add(new NotificationDto(notificationEntity, lessonGridEntities));
                    }
                }
            }
        }

        return notificationDtos;
    }

    /**
     * Удаление уведомления.
     *
     * @param lessonPositionEntity идентификатор.
     */
    @Override
    public void deleteNotification(LessonPositionEntity lessonPositionEntity) {
        LocalDate localDate = LocalDate.now();
        lessonPositionEntity.getNotificationEntities().forEach(notificationEntity -> {
            if (localDate.compareTo(notificationEntity.getDate()) > 0) {
                notificationRepository.delete(notificationEntity);
            }
        });

    }

}
