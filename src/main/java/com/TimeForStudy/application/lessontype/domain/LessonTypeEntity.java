package com.TimeForStudy.application.lessontype.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Доменная модель <strong>Тип занятия</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson_type")
public class LessonTypeEntity {

    /**
     * Идентификатор типа занятия.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson_type")
    @SequenceGenerator(name="seq_lesson_type", sequenceName="SEQ_LESSON_TYPE", allocationSize = 1)
    private Long id;
    /**
     * Наименование типа занятия.
     */
    @Column(name = "name")
    private String name;

}
