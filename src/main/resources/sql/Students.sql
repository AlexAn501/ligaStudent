create sequence if not exists student_seq start 1;

create table if not exists students
(
    id          bigint not null default nextval('student_seq':: regclass),
    first_name  text   not null,
    middle_name text   not null,
    last_name   text   not null,
    speciality  text   not null,
    course      int    not null,
    username       text   not null,
    password    text   not null,
    constraint students_id_pk primary key (id)
);

comment on table students is 'Информация о студентах';

comment on column students.id is 'Идентификатор студента';
comment on column students.first_name is 'Имя студента';
comment on column students.middle_name is 'Отчество студента';
comment on column students.last_name is 'Фамилия студента';
comment on column students.speciality is 'Специальность студента';
comment on column students.course is 'Курс';
comment on column students.username is 'Логин студента';
comment on column students.password is 'Пароль';
