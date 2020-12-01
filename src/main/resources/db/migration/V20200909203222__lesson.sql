--Занятие--
create sequence seq_lesson;

create table lesson
(
    id                bigint     not null,
    subject_id        bigint     not null,
    classroom_id      bigint     not null,
    professor_id      bigint     not null,
    lesson_type_id    bigint     not null,
    semester_id       bigint     not null,
    constraint lesson_pk primary key (id),
    constraint lesson_subject_id_fk foreign key (subject_id) references subject (id) on delete cascade,
    constraint lesson_classroom_id_fk foreign key (classroom_id) references classroom (id) on delete cascade,
    constraint lesson_user_id_fk foreign key (professor_id) references user_tb (id) on delete cascade,
    constraint lesson_lesson_type_id_fk foreign key (lesson_type_id) references lesson_type (id) on delete cascade,
    constraint lesson_semester_id_fk foreign key (semester_id) references semester (id) on delete cascade
);

comment on table lesson is 'Учебное занятие';
comment on column lesson.id is 'Идентификатор учебного занятия';
comment on column lesson.subject_id is 'Идентификатор учебной дисциплины';
comment on column lesson.classroom_id is 'Идентификатор учебного помещения';
comment on column lesson.professor_id is 'Идентификатор преподавателя';
comment on column lesson.lesson_type_id is 'Идентификатор вида учебной дисципины';
comment on column lesson.semester_id is 'Идентификатор учебного семестра';
