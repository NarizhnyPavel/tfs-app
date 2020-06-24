package com.TimeForStudy.application.notification.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import com.TimeForStudy.application.notification.domain.NotificationRepository;
import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.notification.model.NotificationDto;
import com.TimeForStudy.application.notification.model.NotificationStringDto;
import com.TimeForStudy.application.notification.service.NotificationService;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
     * Возвращение уведомления по идентификатору.
     *
     * @param id идентификатор.
     * @return уведомление.
     */
    @Override
    public List<NotificationStringDto> getNotificationById(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        List<NotificationStringDto> notificationStringDtos = new ArrayList<>();
        List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                findAllByUniversity(universityEntity);

        for (GroupEntity groupEntity : userEntity.getGroups()) {
            for (LessonEntity lessonEntity : groupEntity.getLessons()) {
                for (LessonPositionEntity lessonPositionEntity : lessonEntity.getLessonPositions()) {
                    for (NotificationEntity notificationEntity : lessonPositionEntity.getNotificationEntities()) {
                        notificationStringDtos.add(new NotificationStringDto(notificationEntity, lessonGridEntities));
                    }
                }
            }
        }

        return notificationStringDtos;
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
    }

    /**
     * Удаление уведомления.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteNotification(LessonPositionEntity id) {
        LocalDate localDate = LocalDate.now();
        for( NotificationEntity notificationEntity: id.getNotificationEntities()) {
            if (localDate.compareTo(notificationEntity.getDate()) > 0) {
                notificationRepository.delete(notificationEntity);
            }
        }
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
