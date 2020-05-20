package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class NotificationStringDto {

    private String title;

    private String message;

    public NotificationStringDto(NotificationEntity notificationEntity, List<LessonGridEntity> lessonGridEntities) {

        this.title = notificationEntity.getLessons().getLesson().getUser().getName();
        String time = "";
        for (LessonGridEntity lessGrid : lessonGridEntities) {
            if (lessGrid.getLessonNumber() == Integer.parseInt(notificationEntity.getLessonPosition().substring(2,3))) {
                time = lessGrid.getTime();
                break;
            }
        }
        if (notificationEntity.isType()==false) {
            this.title = "Занятие отменено:";
            LocalDate localDate = notificationEntity.getDate().minusDays(1);
            this.message = notificationEntity.getSender().getName() + " отменил(а) занятие " +
                    notificationEntity.getLessons().getLesson().getSubject().getName() +
                    " " + localDate.getDayOfMonth() + "." + localDate.getMonth().getValue() +
                    "." + localDate.getYear() + "г. в " + time + ".";
        } else {

            this.title = "Занятие перенесено:";
            String week = "";
            String pos = notificationEntity.getLessonPosition().substring(0,1) + " неделя";
            if (Integer.parseInt(notificationEntity.getLessonPosition().substring(0,1))==0) {
                pos = "Все недели";
            }
            switch (Integer.parseInt(notificationEntity.getLessonPosition().substring(1,2))) {
                case 1:  week = "Понедельник";
                case 2:  week = "Вторник";
                case 3:  week = "Среда";
                case 4:  week = "Четверг";
                case 5:  week = "Пятница";
                case 6:  week = "Суббота";
                default: week = "Воскресенье";
            }
            this.message = notificationEntity.getLessons().getLesson().getUser().getName() +
                    " перенёс(перенесла) занятие " + notificationEntity.getLessons().getLesson().getSubject().getName() +
                    " на <" + pos + ", " + week + ", " + time + ">.";
        }


    }
}
