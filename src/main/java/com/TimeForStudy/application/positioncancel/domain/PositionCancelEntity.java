package com.TimeForStudy.application.positioncancel.domain;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
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
 * Доменная модель <strong>Отменённая пара</strong>
 *
 * @author Velikanov Artyom.
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "position_cancel")
public class PositionCancelEntity {

    /**
     * Идентификатор отменённой пары.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_position_cancel")
    @SequenceGenerator(name="seq_position_cancel", sequenceName="SEQ_POSITION_CANCEL", allocationSize = 1)
    private Long id;
    /**
     *  Время запроса на отмену.
     */
    @Column(name = "date")
    private LocalDate time;
    /**
     *  Номер недели.
     */
    @Column(name = "cancel_week")
    private Integer cancelWeek;
    /**
     * Позиция лекции.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_position_id")
    private LessonPositionEntity lessonPositionEntity;

}
