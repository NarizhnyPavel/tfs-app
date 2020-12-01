--Виды учебных занятий--
create sequence seq_lesson_type;

create table lesson_type
(
    id      bigint   not null,
    name    text     not null,
    constraint lesson_type_pk primary key (id)
);

comment on table lesson_type is 'Виды учебных занятий';
comment on column lesson_type.id is 'Идентификатор вида учебных занятий';
comment on column lesson_type.name is 'Наименование вида учебных занятий';