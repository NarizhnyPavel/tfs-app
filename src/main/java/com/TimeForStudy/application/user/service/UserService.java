package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.domain.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    void saveUser(User user);
    void updateUser(User updated, String name, String phone);
    void deleteUser(User user);
    List<User> findAll();
}

