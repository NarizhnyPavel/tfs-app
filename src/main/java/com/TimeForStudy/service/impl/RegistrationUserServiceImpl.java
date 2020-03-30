package com.TimeForStudy.service.impl;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationUserServiceImpl implements RegistrationUserService {

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public String RegistrationUser(User user, String group) {
        return null;
    }
}