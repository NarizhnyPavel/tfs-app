package com.TimeForStudy.service;

import com.TimeForStudy.pashaNeZnaetKudaPihat.RegistrationService;
import com.TimeForStudy.repository.TimeForStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TimeForStudyServiceImpl implements TimeForStudyService{

    private TimeForStudyRepository repository;

    @Autowired
    public void setProductRepository(TimeForStudyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteNote(Integer id) {

    }

    @Override
    public void registrationUser() {

    }

    @Override
    public void registerFromFile(String URL) {

    }
}