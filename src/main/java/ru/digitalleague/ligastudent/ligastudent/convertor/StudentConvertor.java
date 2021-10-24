package ru.digitalleague.ligastudent.ligastudent.convertor;

import ru.digitalleague.ligastudent.ligastudent.dto.StudentDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.StudentWithTeachersDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherDTO;
import ru.digitalleague.ligastudent.ligastudent.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentConvertor {

    public static StudentDTO fromStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setMiddleName(student.getMiddleName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setSpeciality(student.getSpeciality());
        studentDTO.setCourse(student.getCourse());

        return studentDTO;
    }

    public static StudentWithTeachersDTO fromStudentWithTeachers(Student student) {
        StudentWithTeachersDTO dto = new StudentWithTeachersDTO();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setMiddleName(student.getMiddleName());
        dto.setLastName(student.getLastName());
        dto.setSpeciality(student.getSpeciality());
        dto.setCourse(student.getCourse());

        List<TeacherDTO> teachers = student.getTeachers()
                .stream()
                .map(TeacherConvertor::fromTeacher)
                .collect(Collectors.toList());

        dto.setTeachers(teachers);
        return dto;
    }

}
