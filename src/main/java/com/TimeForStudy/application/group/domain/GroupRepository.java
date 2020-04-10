package com.TimeForStudy.application.group.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link GroupEntity}
 */
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByNumber(String number);
}
