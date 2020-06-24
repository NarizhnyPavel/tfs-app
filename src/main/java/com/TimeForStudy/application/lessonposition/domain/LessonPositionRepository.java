package com.TimeForStudy.application.lessonposition.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
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

    @Query("FROM LessonPositionEntity l left join l.lesson " +
            "left join l.lesson.classroom left join l.lesson.subject left join l.lesson.user " +
            "WHERE (l.week = :week or l.week = 0) and l.days = :day and l.lesson.user.id = :userId " +
            "ORDER by l.number ASC")
    List<LessonPositionEntity> findAllForSelectedUser(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);

    @Query(value = "select new com.TimeForStudy.application.lesson.model.InfoLessonDto" +
            "(l.id, grid.time, l.classroom.number, l.subject.name, l.subject.arc, cancel.id, l.user.name, l.lessonType.name, l.id, l.user.id) " +
            " FROM LessonPositionEntity lesPos left join PositionCancelEntity cancel on lesPos.id = cancel.lessonPositionEntity.id left join lesPos.lesson l " +
            "left join l.subject left join l.classroom left join l.groups g join g.users u " +
            "left join l.semester.university.lessonGrids grid  " +
            "WHERE lesPos.lesson.id = l.id and u.id = :userId  and lesPos.number = grid.lessonNumber and " +
            "(lesPos.week = :week or lesPos.week = 0) and lesPos.days = :day " +
            "order by lesPos.number asc")
    List<InfoLessonDto> findAllForSelectedStudent(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);


}
