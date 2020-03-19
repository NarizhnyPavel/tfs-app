package com.TimeForStudy.controllers;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.service.TimeForStudyService;
import com.TimeForStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeForStudyRestController {

    private UserService service;

    @Autowired
    public void setNoteService(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        return "index";
    }

    @GetMapping("/new")
    public String newUser() {
        return "operations/new";
    }

    @PostMapping("/save")
    public String updateNote(@RequestParam String phone, String name, String mail) {
//        service.saveUser(new User(phone, name, mail));
        return "redirect:/";
    }
}