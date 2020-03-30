package com.TimeForStudy.repository;

import com.TimeForStudy.entity.University;
import com.TimeForStudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
