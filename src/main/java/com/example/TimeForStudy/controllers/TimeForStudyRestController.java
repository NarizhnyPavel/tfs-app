package com.example.TimeForStudy.controllers;

import com.example.TimeForStudy.service.TimeForStudyService;
import com.example.TimeForStudy.service.TimeForStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeForStudyRestController {

    private TimeForStudyService service;

    @Autowired
    public void setTimeForStudyService(TimeForStudyService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        return "index";
    }
}