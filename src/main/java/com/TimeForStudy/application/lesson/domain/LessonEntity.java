package com.TimeForStudy.application.lesson.domain;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.model.AddLessonDto;
import com.TimeForStudy.application.lesson.model.LessonDto;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сущность занятия
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "lesson")
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lesson")
    @SequenceGenerator(name="seq_lesson", sequenceName="SEQ_LESSON", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Статус
     */
    @Column(name = "status")
    private boolean status;

    /**
     * Кабинет, в котором проходит занятие
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;

    /**
     * Предмет преподаваемый на занятии
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    /**
     * Преподаватель, который проводит занятие
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private UserEntity user;

    /**
     * Семестор, в котором проводится данное занятие
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;

    /**
     * Тип занятия
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_type_id")
    private LessonTypeEntity lessonType;

    /**
     * Список позиций в сетке
     */
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<LessonPositionEntity> lessonPositions;

    /**
     * Группы
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson-group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    )
    private List<GroupEntity> groups;


    public LessonEntity(AddLessonDto addLessonDto) {
        this.classroom = ClassroomDto.on(addLessonDto.getClassroom());
        this.subject = SubjectDto.on(addLessonDto.getSubject());
        this.status = addLessonDto.isStatus();
        this.user = UserDto.on(addLessonDto.getUser());
        this.semester = SemesterDto.on(addLessonDto.getSemester());
        this.lessonType = LessonTypeDto.on(addLessonDto.getLessonType());
        this.groups = addLessonDto.getGroups().stream().map(GroupDto::on).collect(Collectors.toList());
    }
}
