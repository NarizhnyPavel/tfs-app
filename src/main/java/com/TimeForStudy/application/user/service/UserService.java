package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.model.RoleDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;

import java.util.List;

/**
 * Интерфейс сервиса запросов к пользователям.
 *
 * @author Velikanov Artyom.
 */
public interface UserService {

    /**
     * Получение списка пользователей.
     *
     * @return список пользователей.
     */
    List<UserDto> findAll();

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    UserDto findById(Long id);

    /**
     * Поиск преподавателей с поиском по подстроке в имени.
     *
     * @param name подстрока.
     * @return список преподвателей.
     */
    List<IdNameDto> getProfessors(String name);

    /**
     * Получение пользователя по телефону
     *
     * @param phone номер телефона.
     * @return пользователь.
     */
    User findByPhone(String phone);

    /**
     * Получение списка групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return список групп.
     */
    List<AddLessonGroup> getUserGroups(Long id);

    /**
     * Получение списка ролей пользователя.
     *
     * @return список ролей.
     */
    List<RoleDto> getUserRoles();

    /**
     * Поиск по ФИО пользователей.
     *
     * @param userName подстрока, по которой осуществляется поиск.
     * @return список пользователей.
     */
    List<UserDto> findByUserName(String userName);

    /**
     * Редактирование информацию о текущем пользователе.
     *
     * @param updateUserDto пользователь.
     */
    void updateCurrentUser(UpdateUserDto updateUserDto);

    /**
     * Смена роли пользователя.
     *
     * @param userId идентификатор пользователя.
     * @param newRole модель выбранной роли.
     */
    void changeUserRole(Long userId, RoleDto newRole);

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    void deleteUser(Long id);

}
