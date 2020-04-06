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
    public String saveUser(User user) {
        //TODO checking for having sent group number
        if (userRepository.findByPhone(user.getPhone())!=null)
        {
            return "alsoRegistered";
        } else {
            userRepository.save(user);
            return "success";
        }
    }
}