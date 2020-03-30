package com.TimeForStudy.service.impl;

import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean CheckPhone(String phone) {
        return null;
    }

    @Override
    public String getNameByPhone(String phone) {
        return null;
    }

    @Override
    public Boolean CheckCode(int code) {
        return null;
    }
}
