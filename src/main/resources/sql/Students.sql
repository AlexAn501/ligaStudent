create sequence if not exists student_seq start 1;

create table if not exists students
(
    student_id bigint not null default nextval('student_seq':: regclass),
    name text not null,
    middle_name text not null,
    last_name text not null,
    speciality text not null,
    course int not null,
    constraint students_id_pk primary key (student_id)
);

comment on table students is 'Информация о студентах';

comment on column students.student_id is 'Идентификатор студента';
comment on column students.name is 'Имя студента';
comment on column students.middle_name is 'Отчество студента';
comment on column students.last_name is 'Фамилия студента';
comment on column students.speciality is 'Специальность студента';
comment on column students.course is 'Курс';
