package com.TimeForStudy.application.university.domain;

import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.user.model.AddUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сущность учебного заведения
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "university")
public class UniversityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_university")
    @SequenceGenerator(name="seq_university", sequenceName="SEQ_UNIVERSITY", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Наименование университета
     */
    @Column(name = "name")
    private String name;

    /**
     * Количество деления недель
     */
    @Column(name = "weeks")
    private int weeks;

    /**
     * Количество учебных дней
     */
    @Column(name = "work_days")
    private String workDays;

    /**
     * Цвет
     */
    @Column(name = "color")
    private String color;

    /**
     * Логотип
     */
    @Column(name = "logotype_url")
    private String logotype;

    /**
     * Длительность одного занятия
     */
    @Column(name = "lesson_duration")
    private int lessonDuration;

    /**
     * Список семестров, данного учебного заведения
     */
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<SemesterEntity> semesters;

    /**
     * Список  сетки занятий
     */
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<LessonGridEntity> lessonGrids;

    public UniversityEntity(AddUniversityDto addUniversityDto) {
        this.name = addUniversityDto.getName();
        this.weeks = addUniversityDto.getWeeks();
        this.workDays = addUniversityDto.getWorkDays();
        this.lessonDuration = addUniversityDto.getLessonDuration();
        this.color = addUniversityDto.getColor();
        this.logotype = addUniversityDto.getLogo();
    }
}
