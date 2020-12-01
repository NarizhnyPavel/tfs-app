--Таблица-связка группы и учебного занятия--
create table lesson_group
(
    lesson_id bigint not null,
    group_id  bigint not null,
    constraint lesson_group_pk primary key (group_id, lesson_id),
    constraint lesson_group_group_fk foreign key (group_id) references group_tb (id) on delete cascade,
    constraint lesson_group_lesson_fk foreign key (lesson_id) references lesson (id) on delete cascade
);

comment on table lesson_group is 'Таблица-связка группы и учебного занятия';
comment on column lesson_group.group_id is 'Идентификатор группы';
comment on column lesson_group.lesson_id is 'Идентификатор учебного занятия';