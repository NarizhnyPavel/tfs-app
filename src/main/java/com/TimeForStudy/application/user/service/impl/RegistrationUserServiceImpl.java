package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.RegisterDto;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

            if (groupRepository.findByNumber(registerDto.getGroup())!=null) {

//                GroupEntity updated = groupRepository.findByNumber(registerDto.getGroup());
//                UserEntity userEntity = new UserEntity();
//                userEntity.setName(registerDto.getName());
//                userEntity.setPhone(registerDto.getPhone());
//                userEntity.setRole(registerDto.getRole());
//                userEntity.getGroups().add(updated);
//                updated.getUsers().add(userEntity);
//                userRepository.save(userEntity);
//                groupRepository.save(updated);
                GroupEntity groupEntity = groupRepository.findById((long) 1)
                        .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
                UserEntity userEntity = userRepository.findById((long) 42)
                        .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
                userEntity.getGroups().add(groupEntity);
                groupEntity.getUsers().add(userEntity);
                userRepository.save(userEntity);
//                groupRepository.save(groupEntity);
                return "success";
            } else {
                return "groupError";
            }
        }
    }
}