package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param addUserDto пользователь
     * @return статус
     */
    @Override
    public String saveUser(AddUserDto addUserDto) {
        if (addUserDto.getGroups()==null) {
            addUserDto.setGroups(null);
            UserEntity user = new UserEntity(addUserDto);
            userRepository.save(user);
            return "success";
        } else {

            if (groupRepository.findByNumber(addUserDto.getGroups().get(0).getNumber())!=null) {
                UserEntity user = new UserEntity(addUserDto);
                userRepository.save(user);
                return "success";
            } else {
                return "groupError";
            }
        }
    }
}