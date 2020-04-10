package com.TimeForStudy.application.notification.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.notification.model.AddNotificationDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import javax.persistence.*;

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
    private int lessonPosition;

    /**
     * Текст уведомления
     */
    @Column(name = "text")
    private String text;

    /**
     * Лекция, к которой относится данное уведомление
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessons;

    /**
     * Отправитель уведомления
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    /**
     * Получатель уведомления
     */
    //TODO подумать над связью
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    public NotificationEntity (AddNotificationDto addNotificationDto) {
        this.lessonPosition = addNotificationDto.getLessonPosition();
        this.text = addNotificationDto.getText();
        this.lessons = LessonDto.on(addNotificationDto.getLessons());
        this.sender = UserDto.on(addNotificationDto.getSender());
        this.receiver = UserDto.on(addNotificationDto.getReceiver());
    }
}
