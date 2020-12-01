package com.TimeForStudy.application.notification.model;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Модель краткой информации об уведомлении.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class NotificationDto {

    /**
     * Текст.
     */
    private String title;
    /**
     * Сообщение.
     */
    private String message;

    public NotificationDto(NotificationEntity notificationEntity, List<LessonGridEntity> lessonGridEntities) {

        this.title = notificationEntity.getLessons().getLesson().getProfessor().getUserInfo().getFullName();
        String time = "";
        for (LessonGridEntity lessGrid : lessonGridEntities) {
            if (lessGrid.getLessonNumber() == Integer.parseInt(notificationEntity.getLessonPosition().substring(2,3))) {
                time = lessGrid.getTime();
                break;
            }
        }
        if (!notificationEntity.isType()) {
            this.title = "Занятие отменено:";
            String month = "";
            String day = "";
            LocalDate localDate = notificationEntity.getDate().minusDays(1);
            if (localDate.getMonth().getValue()<10) {
                month = "0";
            }
            if (localDate.getDayOfMonth()<10) {
                day = "0";
            }
            this.message = notificationEntity.getSender().getUserInfo().getFullName() + " отменил(а) занятие " +
                    notificationEntity.getLessons().getLesson().getSubject().getName() +
                    " " + day + localDate.getDayOfMonth() + "." + month + localDate.getMonth().getValue() +
                    "." + localDate.getYear() + "г. в " + time + ".";
        } else {

            this.title = "Занятие перенесено:";
            String week = "";
            String pos = notificationEntity.getLessonPosition().substring(0,1) + " неделя";
            if (Integer.parseInt(notificationEntity.getLessonPosition().substring(0,1))==0) {
                pos = "Все недели";
            }
            switch (Integer.parseInt(notificationEntity.getLessonPosition().substring(1,2))) {
                case 1:  week = "Понедельник"; break;
                case 2:  week = "Вторник"; break;
                case 3:  week = "Среда"; break;
                case 4:  week = "Четверг"; break;
                case 5:  week = "Пятница"; break;
                case 6:  week = "Суббота"; break;
                default: week = "Воскресенье"; break;
            }
            this.message = notificationEntity.getLessons().getLesson().getProfessor().getUserInfo().getFullName() +
                    " перенёс(перенесла) занятие " + notificationEntity.getLessons().getLesson().getSubject().getName() +
                    " на <" + pos + ", " + week + ", " + time + ">.";
        }
    }
}
