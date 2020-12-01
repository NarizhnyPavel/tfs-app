package com.TimeForStudy.application.semester.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import lombok.AllArgsConstructor;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

/**
 * Доменная модель <strong>Семестр/полугодие/четверть</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "semester")
public class SemesterEntity {

    /**
     * Идентификатор семестра.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_semester")
    @SequenceGenerator(name="seq_semester", sequenceName="SEQ_SEMESTER", allocationSize = 1)
    private Long id;
    /**
     * Дата начала.
     */
    @Column(name = "star_date")
    private LocalDate start;
    /**
     * Дата окончания.
     */
    @Column(name = "end_date")
    private LocalDate end;
    /**
     * Университет, к которому относится семестр.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private UniversityEntity university;
    /**
     * Список занятий семестра.
     */
    //TODO не увер что вообще понадобится
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private Set<LessonEntity> lessons;

}
