package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.domain.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddUserDto {

    private String phone;

    private String name;

    private byte role;

    private Group group;
}
