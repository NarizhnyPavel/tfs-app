--ВременнАя сетка учебного заведения--
create sequence seq_lesson_grid;

create table lesson_grid
(
    id              bigint      not null,
    university_id   integer     not null,
    time            text        not null,
    lesson_number   integer     not null,
    constraint lesson_grid_pk primary key (id),
    constraint lesson_grid_group_university_fk foreign key (university_id) references university (id) on delete cascade
);

comment on table lesson_grid is 'ВременнАя сетка учебного заведения';
comment on column lesson_grid.id is 'Идентификатор сетки';
comment on column lesson_grid.university_id is 'Идентификатор учебного заведения';
comment on column lesson_grid.time is 'Время проведения занятия';
comment on column lesson_grid.lesson_number is 'Номер занятия в сетке';