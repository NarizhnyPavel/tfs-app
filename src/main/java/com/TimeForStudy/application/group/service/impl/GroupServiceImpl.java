package com.TimeForStudy.application.group.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.model.UsersByGroup;
import com.TimeForStudy.application.group.service.GroupService;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.error.ApplicationException;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Реализация сервиса запросов к группам.
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
     * Получение группы по идентификатору.
     *
     * @param id идентификатор группы.
     * @return группа.
     */
    @Override
    public IdNameDto getGroupById(Long id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        return IdNameDto.of(group.getId(), group.getNumber());
    }

    /**
     * Получение списка студентов группы.
     *
     * @param id идентификатор группы.
     * @return список студентов.
     */
    @Override
    //TODO сделать у группы поле Староста
    public List<UsersByGroup> findStudentsByGroupId(Long id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        List<User> users = group.getUsers().stream()
                .sorted(Comparator.comparing(it -> it.getUserInfo().getLastName()))
                .collect(Collectors.toList());
        List<UsersByGroup> usersByGroups = new ArrayList<>();
        IntStream.range(0, users.size())
                .forEach(i -> usersByGroups.add(
                        UsersByGroup.of(
                                i + 1,
                                2,
                                users.get(i).getUserInfo().getFullName(),
                                users.get(i).getPhone()
                        )
                ));
        return usersByGroups;
    }

    /**
     * Добавление группы.
     *
     * @param group группа.
     */
    @Override
    public void saveGroup(IdNameDto group) {
        GroupEntity entity = new GroupEntity();
        entity.setNumber(group.getName());
        groupRepository.save(entity);
    }

    /**
     * Редактирование группы.
     *
     * @param id идентификатор группы.
     * @param group группа.
     */
    @Override
    public void updateGroup(Long id, IdNameDto group) {
        GroupEntity updated = groupRepository.findById(id)
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        if (StringUtils.isNotBlank(group.getName())) {
            updated.setNumber(group.getName());
        }
        groupRepository.save(updated);
    }

    /**
     * Удаление группы.
     *
     * @param id идентификатор группы.
     */
    @Override
    public void deleteGroup(Long id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        if (!CollectionUtils.isEmpty(group.getUsers()))
            throw new ApplicationException(ErrorDescription.GROUP_HAS_STUDENTS);
        groupRepository.deleteById(id);
    }

    /**
     * Получение списка групп.
     *
     * @return список групп.
     */
    @Override
    public List<IdNameDto> findAll() {
        List<GroupEntity> groups = groupRepository.findAll();
        return groups.stream()
                .map(it -> IdNameDto.of(it.getId(), it.getNumber()))
                .collect(Collectors.toList());
    }

    /**
     * Получение списка групп по наименованию.
     *
     * @param name наименование группы.
     * @return список групп.
     */
    @Override
    public List<GroupsDto> findAllGroups(String name) {
        List<GroupsDto> groups = new ArrayList<>();
        groupRepository.findAll().forEach(group -> {
            if (group.getNumber().contains(name)) {
                groups.add(GroupsDto.of(group.getId(), group.getNumber(), 1));
            }
        });
        return groups;
    }

}
