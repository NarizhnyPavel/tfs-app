package com.TimeForStudy.application.university.domain;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.university.model.AddUniversityDto;
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
import java.util.List;

/**
 * Доменная модель <strong>Учебное заведение</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "university")
public class UniversityEntity {

    /**
     * Идентификатор учебного заведения.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_university")
    @SequenceGenerator(name="seq_university", sequenceName="SEQ_UNIVERSITY", allocationSize = 1)
    private Long id;
    /**
     * Наименование университета.
     */
    @Column(name = "name")
    private String name;
    /**
     * Количество деления недель.
     */
    @Column(name = "weeks")
    private Integer weeks;
    /**
     * Количество учебных дней.
     */
    @Column(name = "work_days")
    private String workDays;
    /**
     * Цвет №1.
     */
    @Column(name = "color1")
    private String color1;
    /**
     * Цвет №2.
     */
    @Column(name = "color2")
    private String color2;
    /**
     * Цвет №3.
     */
    @Column(name = "color3")
    private String color3;
    /**
     * Логотип.
     */
    @Column(name = "logotype_url")
    private String logotype;
    /**
     * Длительность одного занятия.
     */
    @Column(name = "lesson_duration")
    private Integer lessonDuration;
    /**
     * Список семестров, данного учебного заведения.
     */
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<SemesterEntity> semesters;
    /**
     * Список  сетки занятий.
     */
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<LessonGridEntity> lessonGrids;

    public UniversityEntity(AddUniversityDto addUniversityDto) {
        this.name = addUniversityDto.getName();
        this.weeks = addUniversityDto.getWeeks();
        String workday = "";
        if (addUniversityDto.getWorkDays().isMonday()) {
            workday+='1';
        }
        if (addUniversityDto.getWorkDays().isTuesday()) {
            workday+='2';
        }
        if (addUniversityDto.getWorkDays().isWednesday()) {
            workday+='3';
        }
        if (addUniversityDto.getWorkDays().isThursday()) {
            workday+='4';
        }
        if (addUniversityDto.getWorkDays().isFriday()) {
            workday+='5';
        }
        if (addUniversityDto.getWorkDays().isSaturday()) {
            workday+='6';
        }
        if (addUniversityDto.getWorkDays().isSunday()) {
            workday+='7';
        }
        this.workDays = workday;
        this.lessonDuration = addUniversityDto.getLessonDuration();
        this.color1 = addUniversityDto.getColor1();
        this.color2 = addUniversityDto.getColor2();
        this.color3 = addUniversityDto.getColor3();
        this.logotype = addUniversityDto.getLogo();
    }
}
