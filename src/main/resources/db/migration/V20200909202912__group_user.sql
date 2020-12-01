--Таблица-связка группы и пользователя--
create table group_user
(
    group_id    bigint    not null,
    user_id     bigint    not null,
    constraint group_user_pk primary key (group_id, user_id),
    constraint group_user_group_fk foreign key (group_id) references group_tb (id) on delete cascade,
    constraint group_user_user_fk foreign key (user_id) references user_tb (id) on delete cascade
);

comment on table group_user is 'Таблица-связка группы и пользователя';
comment on column group_user.group_id is 'Идентификатор группы';
comment on column group_user.user_id is 'Идентификатор пользователя';