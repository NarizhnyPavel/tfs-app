--Информация о пользователе--
create sequence seq_user_info;

create table user_info
(
    id                 bigint          not null,
    first_name         varchar(255)            ,
    last_name          varchar(255)            ,
    patronymic_name    varchar(255)            ,
    constraint user_info_pk primary key (id)
);

comment on table user_info is 'Информация о пользователе';
comment on column user_info.id is 'Идентификтатор информации о пользователе';
comment on column user_info.first_name is 'Имя пользователя';
comment on column user_info.last_name is 'Фамилия пользователя';
comment on column user_info.patronymic_name is 'Отчество пользователя';