create table if not exists teachers_roles
(
    teacher_id bigint not null,
    role_id bigint not null,

    constraint teachers_roles_pk primary key (role_id, teacher_id),

    constraint role_id_fk foreign key (role_id)
        references roles (id),

    constraint teacher_id_fk foreign key (teacher_id)
        references teachers (id)
);

comment on table teachers_roles is 'Информация о связи учитель-роль';

comment on column teachers_roles.role_id is 'Идентификатор роли';
comment on column teachers_roles.teacher_id is 'Идентификатор учителя';