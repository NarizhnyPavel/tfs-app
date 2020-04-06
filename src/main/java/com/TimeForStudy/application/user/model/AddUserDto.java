package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.domain.Group;
import lombok.Data;

@Data
public class AddUserDto {

    private String phone;

    private String name;

    private byte role;

    private Group group;
}
