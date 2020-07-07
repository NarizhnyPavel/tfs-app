package com.TimeForStudy.application.lessonposition.domain;


import com.TimeForStudy.application.lesson.model.InfoLessonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link LessonPositionEntity}
 */
public interface LessonPositionRepository extends JpaRepository<LessonPositionEntity, Long> {

    List<LessonPositionEntity> findAllByWeek(Integer position);

    List<LessonPositionEntity> findAllByWeekAndDaysAndNumber(Integer position, Integer days, Integer number);

    List<LessonPositionEntity> findAllByWeekAndDays(Integer week, Integer days);

    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.user left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.number = grid.lessonNumber and lesPos.days = :day and l.user.id = :userId " +
            "GROUP BY lesPos.id, lesPos.number, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id " +
            "ORDER by lesPos.number ASC")
    List<InfoLessonDto> findAllForSelectedTeacher(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);

    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.groups g left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.number = grid.lessonNumber and lesPos.days = :day and g.id = :groupId " +
            "GROUP BY lesPos.id, lesPos.number, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id " +
            "ORDER by lesPos.number ASC ")
    List<InfoLessonDto> findAllForSelectedGroup(@Param("week") Integer week, @Param("day") Integer days, @Param("groupId") Long groupId);

    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id) " +
            " FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.subject left join l.classroom left join l.groups g join g.users u " +
            "left join l.semester.university.lessonGrids grid  " +
            "WHERE lesPos.lesson.id = l.id and u.id = :userId  and lesPos.number = grid.lessonNumber and " +
            "((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) and lesPos.days = :day " +
            "GROUP BY lesPos.id, lesPos.number, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id " +
            "order by lesPos.number asc")
    List<InfoLessonDto> findAllForSelectedStudent(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);

    @Query("from LessonPositionEntity pos where pos.id = :id")
    LessonPositionEntity getById(@Param("id") Long id);

    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.groups g left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not(lesPos.week = 0)) or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.days = :day and l.classroom.id = :classroomId and lesPos.number = grid.lessonNumber " +
            "GROUP BY lesPos.id, lesPos.number, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id " +
            "ORDER by lesPos.number ASC")
    List<InfoLessonDto> findAllByForSelectedClassroom(@Param("week") Integer week, @Param("day") Integer days, @Param("classroomId") Long classroomId);
}
