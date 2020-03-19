package com.TimeForStudy.repository;

import com.TimeForStudy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByOrderByDateAsc();
    List<User> findAllByOrderByDateDesc();

}
