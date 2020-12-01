package com.TimeForStudy.application.lesson.domain;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Доменная модель <strong>Учебное занятие</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson")
public class LessonEntity {

    /**
     * Идентификатор учебного занятия.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson")
    @SequenceGenerator(name="seq_lesson", sequenceName="SEQ_LESSON", allocationSize = 1)
    private Long id;
    /**
     * Кабинет, в котором проходит занятие.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;
    /**
     * Предмет преподаваемый на занятии.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;
    /**
     * Преподаватель, который проводит занятие.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private User professor;
    /**
     * Семестор, в котором проводится данное занятие.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;
    /**
     * Тип занятия.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_type_id")
    private LessonTypeEntity lessonType;
    /**
     * Список позиций в сетке.
     */
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<LessonPositionEntity> lessonPositions;
    /**
     * Группы.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = GroupEntity.class)
    @JoinTable(
            name = "lesson_group",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    private List<GroupEntity> groups = new ArrayList<>();

}
