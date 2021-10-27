create sequence if not exists roles_seq start 1;

create table if not exists roles
(
    id        bigint not null default nextval('roles_seq':: regclass),
    role_name text   not null,
    constraint role_id_pk primary key (id)
);

