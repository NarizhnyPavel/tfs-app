package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности пользователь
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * Возвращение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public UserDto getUserById(long id) {
        return null;
    }

    /**
     * Сохранение пользователя.
     *
     * @param addUserDto пользователь.
     */
    @Override
    public void saveUser(AddUserDto addUserDto) {

    }

    /**
     * Изменение значений пользователя.
     *
     * @param id идентификатор.
     * @param addUserDto пользователь.
     */
    @Override
    public void updateUser(long id, AddUserDto addUserDto) {

    }

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUser(long id) {

    }

    /**
     * Возвращение всех существующих пользователей.
     *
     * @return список пользователей.
     */
    @Override
    public List<UserDto> findAll() {
        return null;
    }
}
