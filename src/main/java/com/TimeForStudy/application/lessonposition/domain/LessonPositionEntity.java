package com.TimeForStudy.application.lessonposition.domain;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Доменная модель <strong>Позиция занятия</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_position")
public class LessonPositionEntity {

    /**
     * Идентификатор позиции занятия.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_position")
    @SequenceGenerator(name = "seq_lesson_position", sequenceName = "SEQ_LESSON_POSITION", allocationSize = 1)
    private Long id;
    /**
     * Лекция.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;
    /**
     * Номер недели.
     */
    @Column(name = "week")
    private Integer week;
    /**
     * Номер.
     */
    @Column(name = "lesson_number")
    private Integer lessonNumber;
    /**
     * День.
     */
    @Column(name = "day_number")
    private Integer dayNumber;
    /**
     * Список связанных уведомлений.
     */
    @OneToMany(mappedBy = "lessons", fetch = FetchType.LAZY)
    private List<NotificationEntity> notificationEntities;
    /**
     * Кабинет, в котором проходит занятие.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;

}
