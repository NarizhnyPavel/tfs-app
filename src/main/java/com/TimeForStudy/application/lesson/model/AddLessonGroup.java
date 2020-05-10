package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddLessonGroup {

    private long id;

    private String label;

    private int number = 1;

    public static AddLessonGroup of(GroupEntity groupEntity) {
        AddLessonGroup dto = new AddLessonGroup();
        dto.setId(groupEntity.getId());
        dto.setLabel(groupEntity.getNumber());
        return dto;
    }
    public static GroupEntity on(AddLessonGroup addLessonGroup) {
        GroupEntity entity = new GroupEntity();
        entity.setId(addLessonGroup.getId());
        entity.setNumber(addLessonGroup.getLabel());
        return entity;
    }
}
