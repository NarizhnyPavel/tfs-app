package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UpdateUserDto {
    private long id;
    private String name;
    private String phone;
    private List<AddLessonGroup> groups;
}
