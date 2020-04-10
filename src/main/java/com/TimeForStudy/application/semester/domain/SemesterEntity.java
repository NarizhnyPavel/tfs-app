package com.TimeForStudy.application.semester.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.semester.model.AddSemesterDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * Сущность семестра/полугодия/четверти
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "semester")
public class SemesterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_semester")
    @SequenceGenerator(name="seq_semester", sequenceName="SEQ_SEMESTER", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Дата начала
     */
    @Column(name = "start")
    private LocalDate start;

    /**
     * Дата окончания
     */
    @Column(name = "end")
    private LocalDate end;

    /**
     * Университет, к которому относится семестр
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

    /**
     * Список занятий семестра
     */
    //TODO не увер что вообще понадобится
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

    public SemesterEntity(AddSemesterDto addSemesterDto) {
        this.start = addSemesterDto.getStart();
        this.end = addSemesterDto.getEnd();
        this.university = UniversityDto.on(addSemesterDto.getUniversity());
    }
}
