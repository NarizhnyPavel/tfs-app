package com.TimeForStudy.security.jwt;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserInfo;
import com.TimeForStudy.application.user.domain.UserRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
public final class JwtUserFactory  {

    public  static  JwtUser create (User user){
        UserInfo info = user.getUserInfo();
        return new JwtUser(
                user.getId(),
                info.getFirstName(),
                info.getLastName(),
                info.getPatronymic(),
                user.getPhone(),
                user.getPassword(),
                true,
                user.getLastUpdateDate(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static GrantedAuthority mapToGrantedAuthorities(UserRole role){
        return new SimpleGrantedAuthority(role.getName());
    }

}
