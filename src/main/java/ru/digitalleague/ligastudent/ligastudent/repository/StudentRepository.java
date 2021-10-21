package ru.digitalleague.ligastudent.ligastudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalleague.ligastudent.ligastudent.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
