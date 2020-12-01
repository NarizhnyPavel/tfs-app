package com.TimeForStudy.application.notification.domain;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Доменная модель <strong>Уведомление</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "notification")
public class NotificationEntity {

    /**
     * Идентификатор уведомления.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notification")
    @SequenceGenerator(name="seq_notification", sequenceName="SEQ_NOTIFICATION", allocationSize = 1)
    private Long id;
    /**
     * Позиция занятия.
     */
    @Column(name = "lesson_position")
    private String lessonPosition;
    /**
     * Текст уведомления.
     */
    @Column(name = "date")
    private LocalDate date;
    /**
     * Тип уведомления (false - уведомление; true - запрос).
     */
    @Column(name = "type")
    private boolean type;
    /**
     * Лекция, к которой относится данное уведомление.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonPositionEntity lessons;
    /**
     * Отправитель уведомления.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

}
