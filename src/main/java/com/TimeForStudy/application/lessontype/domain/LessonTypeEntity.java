package com.TimeForStudy.application.lessontype.domain;

import com.TimeForStudy.application.lessontype.model.AddLessonTypeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность типа занятия
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_type")
public class LessonTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_type")
    @SequenceGenerator(name="seq_lesson_type", sequenceName="SEQ_LESSON_TYPE", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Наименование типа занятия
     */
    @Column(name = "name")
    private String name;

    public LessonTypeEntity (AddLessonTypeDto addLessonTypeDto) {
        this.name = addLessonTypeDto.getName();
    }
}
