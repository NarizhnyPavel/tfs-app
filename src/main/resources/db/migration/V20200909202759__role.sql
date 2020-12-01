--Роль пользователя--
create sequence seq_user_role;

create table role
(
    id      bigint           not null,
    name    varchar(255)             ,
    rank    integer                  ,
    constraint user_role_pk primary key (id)
);

comment on table role is 'Роль пользователя';
comment on column role.id is 'Идентификатор роли пользователя';
comment on column role.name is 'Наименование роли пользователя';
comment on column role.rank is 'Ранг роли пользователя';