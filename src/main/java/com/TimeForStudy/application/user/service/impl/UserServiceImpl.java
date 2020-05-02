package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности пользователь
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * Возвращение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public UserDto getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        return UserDto.of(userEntity);
    }

    /**
     * Сохранение пользователя.
     *
     * @param addUserDto пользователь.
     */
    @Override
    public void saveUser(AddUserDto addUserDto) {
        for (GroupDto group : addUserDto.getGroups()) {
            groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        }
        UserEntity userEntity = new UserEntity(addUserDto);
        userRepository.save(userEntity);
    }

    /**
     * Изменение значений пользователя.
     *
     * @param id         идентификатор.
     * @param addUserDto пользователь.
     */
    @Override
    public void updateUser(long id, AddUserDto addUserDto) {
        UserEntity updated = userRepository.findById(id)
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        if (addUserDto.getGroups() != null) {
            for (GroupDto group : addUserDto.getGroups()) {
                groupRepository.findById(group.getId())
                        .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            }
            updated.setGroups(addUserDto.getGroups().stream().map(GroupDto::on).collect(Collectors.toList()));
        }
        if (addUserDto.getPhone() != null) {
            updated.setPhone(addUserDto.getPhone());
        }
        if (addUserDto.getName() != null) {
            updated.setName(addUserDto.getName());
        }
        if (addUserDto.getRole() != 0) {
            ErrorDescription.ROLE_DOES_NOT_MATCH
                    .throwIfTrue((addUserDto.getRole() == 2) && (addUserDto.getRole() == 2));
            updated.setRole(addUserDto.getRole());
        }
        userRepository.save(updated);
    }

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих пользователей.
     *
     * @return список пользователей.
     */
    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserDto::of).collect(Collectors.toList());
    }

    /**
     * Возвращение преподавателей.
     *
     * @return список преподавателей.
     */
    public List<ProfessorDto> findAllProfessors(String name) {

        List<UserEntity> userEntities = userRepository.findAllByRole((byte) 2);
        List<ProfessorDto> professorDtos = new ArrayList<>();
        for (UserEntity professor : userEntities) {
            if (professor.getName().contains(name)) {
                professorDtos.add(new ProfessorDto(professor.getId(), professor.getName()));
            }
        }
        return professorDtos;
    }
}
