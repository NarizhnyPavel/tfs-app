--Уведомление--
create sequence seq_notification;

create table notification
(
    id              bigint    not null,
    lesson_id       bigint    not null,
    sender_id       bigint    not null,
    lesson_position text      not null,
    date            date      not null,
    type            boolean   not null,
    constraint notification_pk primary key (id),
    constraint lesson_fk foreign key (lesson_id) references lesson_position (id),
    constraint sender__fk foreign key (sender_id) references user_tb (id)
);

comment on table notification is 'Уведомление';
comment on column notification.lesson_id is 'Идентификатор учебного зянятия';
comment on column notification.sender_id is 'Идентификатор автора уведомления';
comment on column notification.lesson_position is 'Новая позиция занятия';
comment on column notification.date is 'Время создания';
comment on column notification.type is 'Тип уведомления: true - перенос, false - отмена';

