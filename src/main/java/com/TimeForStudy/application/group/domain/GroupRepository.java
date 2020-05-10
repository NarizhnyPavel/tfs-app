package com.TimeForStudy.application.group.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link GroupEntity}
 */
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByNumber(String number);

}
