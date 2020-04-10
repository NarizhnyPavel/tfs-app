package com.TimeForStudy.application.lessongrid.domain;

import com.TimeForStudy.application.lessongrid.model.AddLessonGridDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Сущность места сетки занятия
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_grid")
public class LessonGridEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_grid")
    @SequenceGenerator(name="seq_lesson_grid", sequenceName="SEQ_LESSON_GRID", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Сборный номер лекции (123: 1 - номер недели, 2 - номер дня недели, 3 - номер пары)
     */
    @Column(name = "lesson_number")
    private int lessonNumber;

    /**
     * Время лекции
     */
    @Column(name = "time")
    private String time;

    /**
     * Университет
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

    public LessonGridEntity(AddLessonGridDto addLessonGridDto) {
        this.lessonNumber = addLessonGridDto.getLessonNumber();
        this.time = addLessonGridDto.getTime();
        this.university = UniversityDto.on(addLessonGridDto.getUniversity());
    }
}
