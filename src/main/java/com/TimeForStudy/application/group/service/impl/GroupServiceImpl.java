package com.TimeForStudy.application.group.service.impl;

import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности группа
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * Возвращение группы по идентификатору.
     *
     * @param id идентификатор.
     * @return группа.
     */
    @Override
    public GroupDto getGroupById(long id) {
        return null;
    }

    /**
     * Сохранение группы.
     *
     * @param addGroupDto группа.
     */
    @Override
    public void saveGroup(AddGroupDto addGroupDto) {

    }

    /**
     * Изменение значений группы.
     *
     * @param id идентификатор.
     * @param addGroupDto группа.
     */
    @Override
    public void updateGroup(long id, AddGroupDto addGroupDto) {

    }

    /**
     * Удаление группы.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteGroup(long id) {

    }

    /**
     * Возвращение всех существующих групп.
     *
     * @return список групп.
     */
    @Override
    public List<GroupDto> findAll() {
        return null;
    }
}
