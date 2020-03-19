package com.TimeForStudy.repository;

import com.TimeForStudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeForStudyRepository extends JpaRepository<User,Integer> {

}