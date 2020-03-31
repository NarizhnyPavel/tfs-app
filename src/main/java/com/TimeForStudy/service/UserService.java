package com.TimeForStudy.service;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    void saveUser(User user);
    void updateUser(User updated, String name, String phone);
    void deleteUser(User user);
    List<User> findAll();
}

