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

@Service
@RequiredArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {

    public final UserRepository userRepository;

    public final GroupRepository groupRepository;

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