--Учебное заведение--

create sequence seq_university;

create table university
(
    id                  bigint                               not null,
    name                text                                 not null,
    weeks               integer                              not null,
    work_days           text                                 not null,
    lesson_duration     integer                              not null,
    color1              text       default '#000000'::text   not null,
    logotype_url        text                                         ,
    color2              text                                         ,
    color3              text                                         ,
    constraint university_pk primary key (id)
);

comment on table university is 'Учебное заведение';
comment on column university.name is 'название';
comment on column university.weeks is 'количество чередующихся недель';
comment on column university.work_days is 'номера рабочих дней недели: 123 (mon, tue, wed)';
comment on column university.lesson_duration is 'продолжительность занятий в минутах';
comment on column university.color1 is 'корпоративный цвет #1 - основной фон';
comment on column university.logotype_url is 'ссылка на логотип учебного заведения';
comment on column university.color2 is 'корпоративный цвет #2';
comment on column university.color3 is 'корпоративный цвет #3';