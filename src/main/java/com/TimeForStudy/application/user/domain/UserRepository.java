package com.TimeForStudy.application.user.domain;

import com.TimeForStudy.application.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPhone(String phone);

}
