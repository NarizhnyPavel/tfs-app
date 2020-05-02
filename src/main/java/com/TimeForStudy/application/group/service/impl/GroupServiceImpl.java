package com.TimeForStudy.application.group.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.service.GroupService;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        GroupEntity groupEntity = groupRepository.findById(id)
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        return GroupDto.of(groupEntity);
    }

    /**
     * Сохранение группы.
     *
     * @param addGroupDto группа.
     */
    @Override
    public void saveGroup(AddGroupDto addGroupDto) {
        GroupEntity groupEntity = new GroupEntity(addGroupDto);
        groupRepository.save(groupEntity);
    }

    /**
     * Изменение значений группы.
     *
     * @param id идентификатор.
     * @param addGroupDto группа.
     */
    @Override
    public void updateGroup(long id, AddGroupDto addGroupDto) {
        GroupEntity updated = groupRepository.findById(id)
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        if (addGroupDto.getNumber()!=null) {
            updated.setNumber(addGroupDto.getNumber());
        }
        groupRepository.save(updated);
    }

    /**
     * Удаление группы.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteGroup(long id) {
        groupRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих групп.
     *
     * @return список групп.
     */
    @Override
    public List<GroupDto> findAll() {
        List<GroupEntity> groupEntities = groupRepository.findAll();
        return groupEntities.stream().map(GroupDto::of).collect(Collectors.toList());
    }

    /**
     * Возвращение группы.
     *
     * @return список группы.
     */
    @Override
    public List<GroupsDto> findAllGroups(String name) {

        List<GroupEntity> groupEntities = groupRepository.findAll();
        List<GroupsDto> groupsDtos= new ArrayList<>();
        for (GroupEntity group : groupEntities) {
            if (group.getNumber().contains(name)) {
                groupsDtos.add(new GroupsDto(group.getId(), group.getNumber()));
            }
        }
        return groupsDtos;
    }

}
