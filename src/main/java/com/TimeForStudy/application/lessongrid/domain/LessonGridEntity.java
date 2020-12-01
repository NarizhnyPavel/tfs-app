package com.TimeForStudy.application.lessongrid.domain;

import com.TimeForStudy.application.university.domain.UniversityEntity;
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

/**
 * Доменная модель <strong>Сетка расписания</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_grid")
public class LessonGridEntity {

    /**
     * Идентификатор сетки расписания.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_grid")
    @SequenceGenerator(name="seq_lesson_grid", sequenceName="SEQ_LESSON_GRID", allocationSize = 1)
    private Long id;
    /**
     * Номер пары.
     */
    @Column(name = "lesson_number")
    private Integer lessonNumber;
    /**
     * Время лекции.
     */
    @Column(name = "time")
    private String time;
    /**
     * Университет.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

}
