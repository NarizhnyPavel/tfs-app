--Позиция занятия--
create sequence seq_lesson_position;

create table lesson_position
(
    id               bigint                  not null,
    lesson_id        bigint                  not null,
    classroom_id     bigint                  not null,
    week             integer    default 0    not null,
    lesson_number    integer                 not null,
    day_number       integer                 not null,
    constraint lesson_position_pk primary key (id),
    constraint lesson_position_lesson_fk foreign key (lesson_id) references lesson (id),
    constraint lesson_position_classroom_fk foreign key (classroom_id) references classroom (id)
);

comment on table lesson_position is 'Позиция занятия';
comment on column lesson_position.id is 'Идентификатор позиции занятия';
comment on column lesson_position.lesson_id is 'Идентификатор учебного занятия';
comment on column lesson_position.classroom_id is 'Идентификатор учебного помещения';
comment on column lesson_position.week is 'Номер недели';
comment on column lesson_position.lesson_number is 'Номер занятия';
comment on column lesson_position.day_number is 'Номер дня недели';