package com.TimeForStudy.application.group.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.model.UsersByGroup;

import java.util.List;

/**
 * Интерфейс сервиса сервиса запросов к группам
 *
 * @author Velikanov Artyom
 */
public interface GroupService {

    /**
     * Получение группы по идентификатору.
     *
     * @param id идентификатор группы.
     * @return группа.
     */
    IdNameDto getGroupById(Long id);

    /**
     * Получение списка студентов группы.
     *
     * @param id идентификатор группы.
     * @return список студентов.
     */
    List<UsersByGroup> findStudentsByGroupId(Long id);

    /**
     * Добавление группы.
     *
     * @param group группа.
     */
    void saveGroup(IdNameDto group);

    /**
     * Редактирование группы.
     *
     * @param id идентификатор группы.
     * @param group группа.
     */
    void updateGroup(Long id, IdNameDto group);

    /**
     * Удаление группы.
     *
     * @param id идентификатор группы.
     */
    void deleteGroup(Long id);

    /**
     * Получение списка групп.
     *
     * @return список групп.
     */
    List<IdNameDto> findAll();

    /**
     * Получение списка групп по наименованию.
     *
     * @param name наименование группы.
     * @return список групп.
     */
    List<GroupsDto> findAllGroups(String name);
}
