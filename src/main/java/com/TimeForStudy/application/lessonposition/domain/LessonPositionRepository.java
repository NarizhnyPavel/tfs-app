package com.TimeForStudy.application.lessonposition.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
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
            "WHERE (l.week = :week or l.week = 0) and l.days = :day and l.lesson.user.id = :userId")
    List<LessonPositionEntity> findAllForSelectedUser(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);

    @Query("select lesPos FROM LessonPositionEntity lesPos left join lesPos.lesson l join l.groups g join g.users u " +
            "WHERE lesPos.lesson.id = l.id and u.id = :userId and " +
            "lesPos.week = :week or lesPos.week = 0 and lesPos.days = :day")
    List<LessonPositionEntity> findAllForSelectedStudent(@Param("week") Integer week, @Param("day") Integer days, @Param("userId") Long userId);

//    List<LessonPositionEntity> findAllByWeekAndDaysAndNumber();
}
