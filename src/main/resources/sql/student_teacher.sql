create table if not exists student_teacher
(
    teacher_id bigint not null,
    student_id bigint not null,

    constraint student_teacher_pk primary key (teacher_id, student_id),

    constraint teachers_id_fk foreign key (teacher_id)
        references teachers (teacher_id),

    constraint students_id_fk foreign key (student_id)
        references students (student_id)
);

comment on table student_teacher is 'Информация о связи студент-учитель';

comment on column student_teacher.teacher_id is 'Идентификатор учителя';
comment on column student_teacher.student_id is 'Идентификатор студента';