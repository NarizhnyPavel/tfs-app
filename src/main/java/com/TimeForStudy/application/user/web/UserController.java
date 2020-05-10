package com.TimeForStudy.application.user.web;

import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * Возвращает список пользователей.
     *
     * @return список пользователей.
     */
    @GetMapping(value = "/user")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    /**
     * Возвращает список преподавателей.
     *
     * @return список преподавателей.
     */
    @PostMapping(value = "/professors")
    public List<ProfessorDto> getProfessors(@RequestBody String name) {
        return userService.findAllProfessors(name);
    }

    /**
     * Возвращает пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    /**
     * Возвращает групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @GetMapping(value = "/user/groups/{id}")
    public List<AddLessonGroup> getUserGroups(@PathVariable long id) {
        return userService.getUserGroupsById(id);
    }

    /**
     * Добавляет нового пользователя.
     *
     * @param addUserDto пользователь.
     */
    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody AddUserDto addUserDto) {
        userService.saveUser(addUserDto);
    }

    /**
     * Изменяет пользователя.
     *
     * @param updateUserDto пользователь.
     */
    @PostMapping(value = "/user/update")
    public String updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    /**
     * Удаляет пользователя.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/user/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
