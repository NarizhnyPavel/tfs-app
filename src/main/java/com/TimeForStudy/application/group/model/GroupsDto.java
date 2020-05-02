package com.TimeForStudy.application.group.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GroupsDto {

    private String label;

    private long id;


    public GroupsDto(long id, String name) {
        this.id = id;
        this.label = name;
    }

}
