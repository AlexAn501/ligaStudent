create sequence if not exists teacher_seq start 1;

create table if not exists teachers
(
    teacher_id bigint not null default nextval('teacher_seq':: regclass),
    name text not null,
    middle_name text not null,
    last_name text not null,
    chair text not null,
    constraint teachers_id_pk primary key (teacher_id)
);

comment on table teachers is 'Информация о учителях';

comment on column teachers.teacher_id is 'Идентификатор учителя';
comment on column teachers.name is 'Имя учителя';
comment on column teachers.middle_name is 'Отчество учителя';
comment on column teachers.last_name is 'Фамилия учителя';
comment on column teachers.chair is 'Кафедра';
