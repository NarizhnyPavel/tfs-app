--Учебные группы--
create sequence seq_group;

create table group_tb
(
    id     bigint  not null,
    number text    not null,
    constraint group_tb_pk primary key (id)
);

comment on table group_tb is 'Учебные группы';
comment on column group_tb.id is 'Идентификатор учебной группы';
comment on column group_tb.number is 'Номер учебной группы';