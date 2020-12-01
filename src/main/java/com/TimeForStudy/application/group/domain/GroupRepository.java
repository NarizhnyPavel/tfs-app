package com.TimeForStudy.application.group.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link GroupEntity}
 */
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    /**
     * Получение всех групп по номерам.
     *
     * @param numbers список номеров.
     * @return список групп.
     */
    @Query("from GroupEntity g where g.number in :groups")
    List<GroupEntity> findAllByNumber(@Param("groups") List<String> numbers);

}
