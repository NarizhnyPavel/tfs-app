package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

    private String phone;

    private String name;

    private byte role;


    public static UserDto of(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setPhone(user.getPhone());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }
}
