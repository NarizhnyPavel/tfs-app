package com.TimeForStudy.application.group.service;

import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UserDto;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Сервис CRUD запросов к сущности группа
 *
 * @author Velikanov Artyom
 */
public interface GroupService {

    /**
     * Возвращение группы по идентификатору.
     *
     * @param id идентификатор.
     * @return группа.
     */
    GroupDto getGroupById(long id);

    /**
     * Возвращает список студентов группы.
     *
     * @return список групп.
     */
    List<UserDto> findStudentsByGroupId(long id);


    /**
     * Сохранение группы.
     *
     * @param addGroupDto группа.
     */
    void saveGroup(AddGroupDto addGroupDto);

    /**
     * Изменение значений группы.
     *
     * @param id идентификатор.
     * @param addGroupDto группа.
     */
    void updateGroup(long id, AddGroupDto addGroupDto);

    /**
     * Удаление группы.
     *
     * @param id идентификатор.
     */
    void deleteGroup(long id);

    /**
     * Возвращение всех существующих групп.
     *
     * @return список групп.
     */
    List<GroupDto> findAll();

    /**
     * Возвращение группы.
     *
     * @return список группы.
     */
    List<GroupsDto> findAllGroups(String name);
}
