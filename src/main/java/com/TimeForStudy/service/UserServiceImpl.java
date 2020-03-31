package com.TimeForStudy.service;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Autowired
    public void setProductRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Integer id) {
          return repository.findById(id).get();
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User updated, String name, String phone) {
        updated.setName(name);
        updated.setPhone(phone);
        repository.save(updated);
    }


    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
