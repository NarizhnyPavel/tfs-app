package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Модель для добавления сущности занятие
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonDto {

    /**
     *  Кабинет, в котором проходит занятие
     */
    private ClassroomDto classroom;

    /**
     * Предмет преподаваемый на занятии
     */
    private SubjectDto subject;

    /**
     * Преподаватель, который проводит занятие
     */
    private UserDto user;

    /**
     * Семестор, в котором проводится данное занятие
     */
    private SemesterDto semester;

    /**
     * Тип занятия
     */
    private LessonTypeDto lessonType;

    /**
     * Группы
     */
    private List<GroupDto> groups;
}
