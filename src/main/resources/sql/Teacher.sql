create sequence if not exists teacher_seq start 1;

create table if not exists teachers
(
    id          bigint not null default nextval('teacher_seq':: regclass),
    first_name  text   not null,
    middle_name text   not null,
    last_name   text   not null,
    chair       text   not null,
    username       text   not null,
    password    text   not null,
    constraint teachers_id_pk primary key (id)
);

comment on table teachers is 'Информация о учителях';

comment on column teachers.id is 'Идентификатор учителя';
comment on column teachers.first_name is 'Имя учителя';
comment on column teachers.middle_name is 'Отчество учителя';
comment on column teachers.last_name is 'Фамилия учителя';
comment on column teachers.chair is 'Кафедра';
comment on column teachers.username is 'Логин учителя';
comment on column teachers.password is 'Пароль';

