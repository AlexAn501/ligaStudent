package ru.digitalleague.ligastudent.ligastudent.api;

import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;


public interface StudentService {

    Student getStudent(long id);

    void saveOrUpdateStudent(Student student);

    void deleteStudent(long id);

    List<Student> getAllStudent();

    List<Teacher> getAllTeacherFromStudent(long id);
}
