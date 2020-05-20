package com.TimeForStudy.application.notification.domain;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.model.LessonPositionDto;
import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Сущность уведомления
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notification")
    @SequenceGenerator(name="seq_notification", sequenceName="SEQ_NOTIFICATION", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Позиция занятия
     */
    @Column(name = "lesson_position")
    private String lessonPosition;

    /**
     * Текст уведомления
     */
    @Column(name = "date")
    private LocalDate date;

    /**
     * Тип уведомления (false - уведомление; true - запрос)
     */
    @Column(name = "type")
    private boolean type;

    /**
     * Лекция, к которой относится данное уведомление
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonPositionEntity lessons;

    /**
     * Отправитель уведомления
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    public NotificationEntity (AddNotificationDto addNotificationDto) {
        this.lessonPosition = addNotificationDto.getLessonPosition();
        this.date = addNotificationDto.getDate();
        this.lessons = LessonPositionDto.on(addNotificationDto.getLessons());
        this.sender = UserDto.on(addNotificationDto.getSender());
        this.type = addNotificationDto.isType();
    }
}
