package ru.digitalleague.ligastudent.ligastudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
