package com.TimeForStudy.application.university.domain;

import com.TimeForStudy.application.university.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
