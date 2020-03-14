package com.example.TimeForStudy.repository;

import com.example.TimeForStudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface TimeForStudyRepository extends JpaRepository<User,Integer> {

}