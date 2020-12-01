--Отменённое занятие--
create sequence seq_position_cancel;

create table position_cancel
(
    id                    bigint     not null,
    lesson_position_id    bigint     not null,
    date                  date       not null,
    cancel_week           integer            ,
    constraint position_cancel_pk primary key (id),
    constraint position_cancel_lesson_position_fk foreign key (lesson_position_id) references lesson_position (id) on delete cascade
);

comment on table position_cancel is 'Отменённое занятие';
comment on column position_cancel.id is 'Идентификатор отменённого занятия';
comment on column position_cancel.lesson_position_id is 'Идентификатор позиции занятия';
comment on column position_cancel.date is 'Дата отмены занятия';
comment on column position_cancel.cancel_week is 'Номер недели в случае повтора занятия каждую неделю';