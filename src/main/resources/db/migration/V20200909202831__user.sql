create sequence seq_user;

create table user_tb
(
    id                  bigint            not null,
    role_id             bigint            not null,
    user_info_id        bigint            not null,
    phone               text                      ,
    password            text                      ,
    last_update_date    timestamp                 ,
    constraint user_pk primary key (id),
    constraint user_tb_role_id_fk foreign key (role_id) references role (id) on update cascade on delete cascade,
    constraint user_tb_user_info_id_fk foreign key (user_info_id) references user_info (id) on update cascade on delete cascade
);

comment on table user_tb is 'Пользователь';
comment on column user_tb.id is 'Идентификатор пользователя';
comment on column user_tb.role_id is 'Идентификатор роли пользователя';
comment on column user_tb.user_info_id is 'Идентификатор информации о пользователе';
comment on column user_tb.phone is 'Номер телефона';
comment on column user_tb.password is 'Пароль пользователя';
comment on column user_tb.last_update_date is 'Дата последнего обновления';