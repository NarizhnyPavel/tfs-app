package com.TimeForStudy.application.classroom.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

/**
 * Доменная модель <strong>Кабинет</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "classroom")
public class ClassroomEntity {

    /**
     * Идентификатор кабинета.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_classroom")
    @SequenceGenerator(name="seq_classroom", sequenceName="SEQ_CLASSROOM", allocationSize = 1)
    private Long id;
    /**
     * Номер кабинета.
     */
    @Column(name = "number")
    private String number;
    /**
     * Список лекций проводимых в этом кабинете.
     */
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

}
