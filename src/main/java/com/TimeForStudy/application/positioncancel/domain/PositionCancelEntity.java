package com.TimeForStudy.application.positioncancel.domain;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Сущность отмены пары в текущей позиции.
 *
 * @author Velikanov Artyom.
 */
@Entity
@Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "position_cancel")
public class PositionCancelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_position_cancel")
    @SequenceGenerator(name="seq_position_cancel", sequenceName="SEQ_POSITION_CANCEL", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     *  Время запроса на отмену.
     */
    @Column(name = "time")
    private LocalDate time;

    /**
     *  Номер недели.
     */
    @Column(name = "cancel_week")
    private int cancelWeek;

    /**
     * Позиция лекции.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_position_id")
    private LessonPositionEntity lessonPositionEntity;

    public PositionCancelEntity(LocalDate time, int cancelWeek, LessonPositionEntity lessonPositionEntity) {
        this.time = time;
        this.cancelWeek = cancelWeek;
        this.lessonPositionEntity = lessonPositionEntity;
    }
}
