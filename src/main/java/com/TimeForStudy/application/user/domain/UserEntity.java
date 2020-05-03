package com.TimeForStudy.application.user.domain;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.otherDataClasses.UserRole;
import com.TimeForStudy.application.otherDataClasses.UserRoles;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.RegisterDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сущность пользователя
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "user_tb")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name="seq_user", sequenceName="SEQ_USER", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Номер телефона
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Фамилия Имя Отчество
     */
    @Column(name = "name")
    private String name;

    /**
     * Роль (1 - Диспетчер, 2 - Преподаватель, 3 - Староста, 4 - Студент)
     */
    @Column(name = "role")
    private byte role;

    /**
     * Группа (Если это студент или староста)
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = GroupEntity.class)
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    private List<GroupEntity> groups = new ArrayList<>();


    public UserEntity(String phone, String name, UserRole role) {
        this.phone = phone;
        this.name = name;
        this.role = role.getId();
    }

    public UserEntity(String phone, String name, String groupNumber) {
        this.phone = phone;
        this.name = name;
        this.role = UserRoles.STUDENT.getId();
    }

    public UserEntity(AddUserDto addUserDto) {
        this.name = addUserDto.getName();
        this.phone = addUserDto.getPhone();
        this.role = addUserDto.getRole();
        this.groups = addUserDto.getGroups().stream().map(GroupDto::on).collect(Collectors.toList());
    }

    public UserEntity(RegisterDto registerDto) {
        this.name = registerDto.getName();
        this.phone = registerDto.getPhone();
        this.role = registerDto.getRole();
    }

    public UserEntity(String name, String phone, byte role, List<GroupEntity> groupEntities) {
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.groups = groupEntities;
    }
    public UserEntity(String phone, UserRole role) {
        this.phone = phone;
        this.role = role.getId();
    }
}