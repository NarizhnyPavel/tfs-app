--Семестер--
create sequence seq_semester;

create table semester
(
    id               bigint           not null,
    university_id    integer          not null,
    end_date         date             not null,
    start_date       date             not null,
    constraint semester_pk primary key (id),
    constraint semester_university_fk foreign key (university_id) references university (id) on update cascade on delete cascade
);

comment on table semester is 'Учебный семестр';
comment on column semester.id is 'Идентификатор семестра';
comment on column semester.university_id is 'Идентификатор университета';
comment on column semester.end_date is 'Дата окончания семестра';
comment on column semester.start_date is 'Дата начала семестра';