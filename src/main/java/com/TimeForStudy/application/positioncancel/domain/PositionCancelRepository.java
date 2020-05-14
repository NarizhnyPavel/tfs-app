package com.TimeForStudy.application.positioncancel.domain;

import com.TimeForStudy.application.group.domain.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionCancelRepository extends JpaRepository<PositionCancelEntity, Long> {

    List<PositionCancelEntity> findAllByCancelWeek(int number);
}
