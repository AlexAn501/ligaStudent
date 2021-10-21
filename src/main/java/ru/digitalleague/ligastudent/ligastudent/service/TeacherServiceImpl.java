package ru.digitalleague.ligastudent.ligastudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.ligastudent.ligastudent.api.TeacherService;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;
import ru.digitalleague.ligastudent.ligastudent.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacher(long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElse(null);    }

    @Override
    public void saveOrUpdateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentsFromTeacher(long id) {
        Teacher teacher = getTeacher(id);
        return teacher.getStudents();
    }
}
