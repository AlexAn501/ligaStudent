create table if not exists students_roles
(
    student_id bigint not null,
    role_id bigint not null,

    constraint students_roles_pk primary key (role_id, student_id),

    constraint role_id_fk foreign key (role_id)
        references roles (id),

    constraint student_id_fk foreign key (student_id)
        references students (id)
);

comment on table students_roles is 'Информация о связи студент-роль';

comment on column students_roles.role_id is 'Идентификатор роли';
comment on column students_roles.student_id is 'Идентификатор студента';