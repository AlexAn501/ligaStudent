package ru.digitalleague.ligastudent.ligastudent.convertor;

import ru.digitalleague.ligastudent.ligastudent.dto.*;
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
        studentDTO.setLogin(student.getLogin());
        return studentDTO;
    }

    public static StudentWithTeachersDTO fromStudentWithTeachers(Student student) {
        StudentDTO studentDTO = fromStudent(student);
        StudentWithTeachersDTO dto = new StudentWithTeachersDTO(studentDTO);

        List<TeacherDTO> teachers = student.getTeachers()
                .stream()
                .map(TeacherConvertor::fromTeacher)
                .collect(Collectors.toList());

        dto.setTeachers(teachers);
        return dto;
    }

    public static StudentWithTeachersAndRolesDTO fromStudentWithTeacherAndRoles(Student student) {
        StudentWithTeachersDTO studentTeacherDTO = fromStudentWithTeachers(student);
        StudentWithTeachersAndRolesDTO dto = new StudentWithTeachersAndRolesDTO(studentTeacherDTO);

        List<RoleDTO> roles = student.getRoles()
                .stream()
                .map(RoleConvertor::fromRole)
                .collect(Collectors.toList());
        dto.setRoles(roles);
        return dto;
    }
}
