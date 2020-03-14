package com.example.TimeForStudy.service;

import com.example.TimeForStudy.repository.TimeForStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface TimeForStudyService {
    void deleteNote(Integer id);
}

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
}