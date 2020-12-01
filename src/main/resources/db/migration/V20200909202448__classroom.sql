--Аудитория--
create sequence seq_classroom;

create table classroom
(
    id     bigint      not null,
    number text        not null,
    constraint classroom_pk primary key (id)
);

comment on table classroom is 'Аудитория';
comment on column classroom.id is 'Идентификатор аудитории';
comment on column classroom.number is 'Номер аудитории';