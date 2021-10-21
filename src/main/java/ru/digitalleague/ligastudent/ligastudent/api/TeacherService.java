package ru.digitalleague.ligastudent.ligastudent.api;

import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher getTeacher(long id);

    void saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(long id);

    List<Teacher> getAllTeacher();

    List<Student> getAllStudentsFromTeacher(long id);
}
