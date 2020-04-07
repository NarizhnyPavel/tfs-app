package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.Group;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationUserServiceImpl implements RegistrationUserService {

    public UserRepository userRepository;

    public GroupRepository groupRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public String saveUser(AddUserDto addUserDto) {
        if (addUserDto.getGroup().getNumber()==null) {
            addUserDto.setGroup(null);
            User user = new User(addUserDto);
            userRepository.save(user);
            return "success";
        } else {
            if (addUserDto.getGroup().getNumber().equals("7371")) {
                addUserDto.getGroup().setId(1);
                User user = new User(addUserDto);
                userRepository.save(user);
                return "success";
            } else {
                return "groupError";
            }
        }
    }
}