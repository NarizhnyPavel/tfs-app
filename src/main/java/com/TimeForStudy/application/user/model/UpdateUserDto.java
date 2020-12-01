package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель редактирования пользователя.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class UpdateUserDto {

    /**
     * Имя пользователя.
     */
    private String firstName;
    /**
     * Фамилия пользователя.
     */
    private String lastName;
    /**
     * Отчество пользователя.
     */
    private String patronymic;
    /**
     * Список групп пользователя.
     */
    private List<AddLessonGroup> groups;
}
