--Учебная дисциплина--
create sequence seq_subject;

create table subject
(
    id   bigint    not null,
    name text      not null,
    arc  text      not null,
    constraint subject_pk primary key (id)
);

comment on table subject is 'Учебная дисциплина';
comment on column subject.id is 'Идентификатор дисциплины';
comment on column subject.name is 'Наименование дисциплины';
comment on column subject.arc is 'Сокращённое наименование';