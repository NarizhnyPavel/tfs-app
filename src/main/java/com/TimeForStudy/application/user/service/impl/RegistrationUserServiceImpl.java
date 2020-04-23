package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.RegisterDto;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {

    /**
     * {@link UserRepository}
     */
    public final UserRepository userRepository;

    /**
     * {@link GroupRepository}
     */
    public final GroupRepository groupRepository;

    /**
     *  Проверка и сохранение пользователя
     *
     * @param registerDto регистрация
     * @return статус
     */
    @Override
    public String saveUser(RegisterDto registerDto) {
        if (registerDto.getGroup()==null) {
            UserEntity user = new UserEntity(registerDto);
            userRepository.save(user);
            return "success";
        } else {

            if (groupRepository.findByNumber(registerDto.getGroup().getNumber())!=null) {

                UserEntity user = new UserEntity(registerDto);
                GroupEntity updated = groupRepository.findByNumber(registerDto.getGroup().getNumber());
                List<UserEntity> users = updated.getUsers();
                users.add(user);
                updated.setUsers(users);
                groupRepository.save(updated);
                List<GroupEntity> groups = new ArrayList<>();
                groups.add(updated);
                user.setGroups(groups);
                userRepository.save(user);
                return "success";
            } else {
                return "groupError";
            }
        }
    }
}