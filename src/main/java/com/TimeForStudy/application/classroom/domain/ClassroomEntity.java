package com.TimeForStudy.application.classroom.domain;

import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность кабинета
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "classroom")
public class ClassroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_classroom")
    @SequenceGenerator(name="seq_classroom", sequenceName="SEQ_CLASSROOM", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Номер кабинета
     */
    @Column(name = "number")
    private int number;

    /**
     * Список лекций проводимых в этом кабинете
     */
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

    public ClassroomEntity(AddClassroomDto addClassroomDto) {
        this.number = addClassroomDto.getNumber();
    }
}
