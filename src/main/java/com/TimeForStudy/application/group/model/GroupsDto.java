package com.TimeForStudy.application.group.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GroupsDto {

    private String label;

    private long id;

    private int number;

    public GroupsDto(long id, String name) {
        this.id = id;
        this.label = name;
        this.number = 1;
    }

}
