package com.TimeForStudy.application.lessonposition.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.lessonposition.model.AddLessonPositionDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Сущность позиции занятия
 *
 * @author Velikanov Artyom
 */
@Entity
@Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_position")
public class LessonPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_position")
    @SequenceGenerator(name = "seq_lesson_position", sequenceName = "SEQ_LESSON_POSITION", allocationSize = 1)
    @Column(name = "id")
    private long id;

    /**
     * Лекция
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    /**
     * Позиция
     */
    @Column(name = "position")
    private int position;

    public LessonPositionEntity(AddLessonPositionDto addLessonPositionDto) {
        this.position = addLessonPositionDto.getPosition();
    }
}
