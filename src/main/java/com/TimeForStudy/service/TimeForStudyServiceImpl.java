package com.TimeForStudy.service;

import com.TimeForStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TimeForStudyServiceImpl implements TimeForStudyService{

    private UserRepository repository;

    @Autowired
    public void setProductRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public void registrationUser() {

    }

    @Override
    public void registerFromFile(String URL) {

    }
}