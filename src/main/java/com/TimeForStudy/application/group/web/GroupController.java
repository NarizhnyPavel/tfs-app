package com.TimeForStudy.application.group.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.model.UsersByGroup;
import com.TimeForStudy.application.group.service.GroupService;
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
 * Обработчик запросов группы.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    /**
     * {@link GroupService}
     */
    private final GroupService groupService;

    /**
     * Получение списка групп.
     *
     * @return список групп.
     */
    @GetMapping(value = "/group")
    public List<IdNameDto> getGroups() {
        return groupService.findAll();
    }

    /**
     * Получение списка студентов группы.
     *
     * @param id идентификатор группы.
     * @return список студентов.
     */
    @GetMapping(value = "/teacher/group/students/{id}")
    public List<UsersByGroup> getGroups(@PathVariable Long id) {
        return groupService.findStudentsByGroupId(id);
    }

    /**
     * Получение списка групп по наименованию.
     *
     * @param name наименование группы.
     * @return список групп.
     */
    @PostMapping(value = "/group")
    public List<GroupsDto> postGroups(@RequestBody String name) {
        return groupService.findAllGroups(name);
    }

    /**
     * Получение группы по идентификатору.
     *
     * @param id идентификатор группы.
     * @return группа.
     */
    @GetMapping(value = "/group/{id}")
    public IdNameDto getGroup(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    /**
     * Добавление группы.
     *
     * @param group группа.
     */
    @PostMapping(value = "/admin/group")
    public void addGroup(@RequestBody IdNameDto group) {
        groupService.saveGroup(group);
    }

    /**
     * Редактирование группы.
     *
     * @param id идентификатор группы.
     * @param group группа.
     */
    @PutMapping(value = "/admin/group/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody IdNameDto group) {
        groupService.updateGroup(id, group);
    }

    /**
     * Удаление группы.
     *
     * @param id идентификатор группы.
     */
    @DeleteMapping(value = "/admin/group/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

}
