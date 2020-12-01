package com.TimeForStudy.application.user.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.user.model.RoleDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Обработчик запросов пользователя.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    /**
     * {@link UserService}
     */
    private final UserService userService;

    /**
     * Получение списка пользователей.
     *
     * @return список пользователей.
     */
    @GetMapping(value = "/admin/user")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    /**
     * Поиск по ФИО пользователей.
     *
     * @param name подстрока, по которой осуществляется поиск.
     * @return список пользователей.
     */
    @PostMapping(value = "/admin/user")
    public List<UserDto> getUsers(@RequestBody String name) {
        return userService.findByUserName(name);
    }

    /**
     * Получение списка ролей пользователя.
     *
     * @return список ролей.
     */
    @GetMapping(value = "/admin/user/role")
    public List<RoleDto> getUserRoles() {
        return userService.getUserRoles();
    }

    /**
     * Смена роли пользователя.
     *
     * @param id идентификатор пользователя.
     * @param roleDto модель выбранной роли.
     */
    @PutMapping(value = "/admin/user/role/{id}")
    public void changeUserRole(@PathVariable Long id, @RequestBody RoleDto roleDto){
        userService.changeUserRole(id, roleDto);
    }

    /**
     * Поиск преподавателей с поиском по подстроке в имени.
     *
     * @param name подстрока.
     * @return список преподвателей.
     */
    @PostMapping(value = "/admin/professors")
    public List<IdNameDto> getProfessors(@RequestBody String name) {
        return userService.getProfessors(name);
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @GetMapping(value = "/admin/user/{id}")
    public UserDto getUser(@PathVariable Long id) { return userService.findById(id); }

    /**
     * Получение списка групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return список групп.
     */
    @GetMapping(value = "/user/groups/{id}")
    public List<AddLessonGroup> getUserGroups(@PathVariable Long id) {
        return userService.getUserGroups(id);
    }

     /**
     * Редактирование информацию о текущем пользователе.
     *
     * @param updateUserDto пользователь.
     */
    @PutMapping(value = "/user")
    public void updateUser(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateCurrentUser(updateUserDto);
    }

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/admin/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
