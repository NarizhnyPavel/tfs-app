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

    /**
     * Получение списка позиций занятий по номеру недели
     *
     * @param position номер недели.
     * @return список позиций занятий.
     */
    List<LessonPositionEntity> findAllByWeek(Integer position);

    /**
     * Получение списка позиций занятий по
     *
     * @param position позиция.
     * @param dayNumber номер дня недели.
     * @param lessonNumber номер занятия.
     * @return список позиций занятий.
     */
    List<LessonPositionEntity> findAllByWeekAndDayNumberAndLessonNumber(Integer position, Integer dayNumber, Integer lessonNumber);

    /**
     * Получение списка позиций занятий по
     *
     * @param week номер недели.
     * @param dayNumber номер дня недели.
     * @return список позиций занятий.
     */
    List<LessonPositionEntity> findAllByWeekAndDayNumber(Integer week, Integer dayNumber);

    /**
     * Формирование списка позиций занятий по критериям.
     *
     * @param week номер недели.
     * @param day номер дня недели.
     * @param userId идентификатор преподавателя.
     * @return список позиций.
     */
    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.professor left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.lessonNumber = grid.lessonNumber and lesPos.dayNumber = :day and l.professor.id = :userId " +
            "GROUP BY lesPos.id, lesPos.lessonNumber, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id ORDER by lesPos.lessonNumber ASC")
    List<InfoLessonDto> findAllForSelectedTeacher(@Param("week") Integer week, @Param("day") Integer day, @Param("userId") Long userId);

    /**
     * Формирование списка позиций занятий по критериям.
     *
     * @param week номер недели.
     * @param day номер дня недели.
     * @param groupId идентификатор учебной группы.
     * @return список позиций.
     */
    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.groups g left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.lessonNumber = grid.lessonNumber and lesPos.dayNumber = :day and g.id = :groupId " +
            "GROUP BY lesPos.id, lesPos.lessonNumber, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id ORDER by lesPos.lessonNumber ASC ")
    List<InfoLessonDto> findAllForSelectedGroup(@Param("week") Integer week, @Param("day") Integer day, @Param("groupId") Long groupId);

    /**
     * Формирование списка позиций занятий по критериям.
     *
     * @param week номер недели.
     * @param day номер дня недели.
     * @param userId идентификатор студента.
     * @return список позиций.
     */
    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id) " +
            " FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.subject left join l.classroom left join l.groups g join g.users u " +
            "left join l.semester.university.lessonGrids grid  " +
            "WHERE lesPos.lesson.id = l.id and u.id = :userId  and lesPos.lessonNumber = grid.lessonNumber and " +
            "((lesPos.week = :week and not (lesPos.week = 0))  or (not(lesPos.week = :week) and lesPos.week = 0)) and lesPos.dayNumber = :day " +
            "GROUP BY lesPos.id, lesPos.lessonNumber, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id order by lesPos.lessonNumber asc")
    List<InfoLessonDto> findAllForSelectedStudent(@Param("week") Integer week, @Param("day") Integer day, @Param("userId") Long userId);

    /**
     * Получение позиции занятия по идентификатору
     *
     * @param id идентификатор позиции.
     * @return позиция занятия.
     */
    @Query("from LessonPositionEntity pos where pos.id = :id")
    LessonPositionEntity getById(@Param("id") Long id);

    /**
     * Формирование списка позиций занятий по критериям.
     *
     * @param week номер недели.
     * @param day номер дня недели.
     * @param classroomId идентификатор учебного помещения.
     * @return список позиций.
     */
    @Query("select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(lesPos.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id," +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id) " +
            "FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.classroom left join l.subject left join l.groups g left join l.semester.university.lessonGrids grid " +
            "WHERE ((lesPos.week = :week and not(lesPos.week = 0)) or (not(lesPos.week = :week) and lesPos.week = 0)) " +
            "and lesPos.dayNumber = :day and l.classroom.id = :classroomId and lesPos.lessonNumber = grid.lessonNumber " +
            "GROUP BY lesPos.id, lesPos.lessonNumber, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, " +
            "concat(l.professor.userInfo.lastName, ' ', l.professor.userInfo.firstName, ' ', l.professor.userInfo.patronymic), " +
            "l.lessonType.name, l.id, l.professor.id ORDER by lesPos.lessonNumber ASC")
    List<InfoLessonDto> findAllByForSelectedClassroom(@Param("week") Integer week, @Param("day") Integer day, @Param("classroomId") Long classroomId);
}
