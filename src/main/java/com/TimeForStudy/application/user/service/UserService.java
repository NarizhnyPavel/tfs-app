package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности пользователь
 *
 * @author Velikanov Artyom
 */
public interface UserService {

    /**
     * Возвращение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    UserDto getUserById(long id);

    /**
     * Возвращает групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    List<AddLessonGroup> getUserGroupsById(long id);

    /**
     * Сохранение пользователя.
     *
     * @param addUserDto пользователь.
     */
    void saveUser(AddUserDto addUserDto);

    /**
     * Изменение значений пользователя.
     *
     * @param updateUserDto идентификатор.
     */
    String updateUser(UpdateUserDto updateUserDto);

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    void deleteUser(long id);

    /**
     * Возвращение всех существующих пользователей.
     *
     * @return список пользователей.
     */
    List<UserDto> findAll();

    /**
     * Возвращение преподавателей.
     *
     * @return список преподавателей.
     */
    List<ProfessorDto> findAllProfessors(String name);
}

