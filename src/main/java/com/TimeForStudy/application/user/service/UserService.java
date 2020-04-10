package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;

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
     * Сохранение пользователя.
     *
     * @param addUserDto пользователь.
     */
    void saveUser(AddUserDto addUserDto);

    /**
     * Изменение значений пользователя.
     *
     * @param id идентификатор.
     * @param addUserDto пользователь.
     */
    void updateUser(long id, AddUserDto addUserDto);

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
}

