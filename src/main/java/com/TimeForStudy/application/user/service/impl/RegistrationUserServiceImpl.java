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
     * @param addUserDto
     * @return статус
     */
    @Override
    public String saveUser(AddUserDto addUserDto) {
        if (addUserDto.getGroup().getNumber()==null) {
            addUserDto.setGroup(null);
            UserEntity user = new UserEntity(addUserDto);
            userRepository.save(user);
            return "success";
        } else {

            if (groupRepository.findByNumber(addUserDto.getGroup().getNumber())!=null) {
                addUserDto.setGroup(GroupDto.of(groupRepository.findByNumber(addUserDto.getGroup().getNumber())));
                UserEntity user = new UserEntity(addUserDto);
                userRepository.save(user);
                return "success";
            } else {
                return "groupError";
            }
        }
    }
}