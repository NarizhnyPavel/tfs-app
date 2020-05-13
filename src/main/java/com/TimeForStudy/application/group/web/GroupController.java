package com.TimeForStudy.application.group.web;

import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.service.GroupService;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * Возвращает список групп.
     *
     * @return список групп.
     */
    @GetMapping(value = "/group")
    public List<GroupDto> getGroups() {
        return groupService.findAll();
    }

    /**
     * Возвращает список студентов группы.
     *
     * @return список групп.
     */
    @GetMapping(value = "/group/students/{id}")
    public List<UserDto> getGroups(@PathVariable long id) {
        return groupService.findStudentsByGroupId(id);
    }

    /**
     * Возвращает список групп.
     *
     * @return список групп.
     */
    @PostMapping(value = "/groups")
    public List<GroupsDto> postGroups(@RequestBody String name) {
        return groupService.findAllGroups(name);
    }


    /**
     * Возвращает группу по идентификатору.
     *
     * @param id идентификатор.
     * @return группа
     */
    @GetMapping(value = "/group/{id}")
    public GroupDto getGroup(@PathVariable long id) {
        return groupService.getGroupById(id);
    }

    /**
     * Добавляет новую группу.
     *
     * @param addGroupDto группа.
     */
    @PostMapping(value = "/group/add")
    public void addGroup(@RequestBody AddGroupDto addGroupDto) {
        groupService.saveGroup(addGroupDto);
    }

    /**
     * Изменяет данную группу.
     *
     * @param id идентификатор.
     * @param addGroupDto группа.
     */
    @PutMapping(value = "/group/update/{id}")
    public void updateGroup(@PathVariable long id, @RequestBody AddGroupDto addGroupDto) {
        groupService.updateGroup(id, addGroupDto);
    }

    /**
     * Удаляет группу.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/group/delete/{id}")
    public void deleteGroup(@PathVariable long id) {
        groupService.deleteGroup(id);
    }

}
