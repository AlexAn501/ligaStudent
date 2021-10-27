package ru.digitalleague.ligastudent.ligastudent.convertor;

import ru.digitalleague.ligastudent.ligastudent.dto.*;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherConvertor {

    public static TeacherDTO fromTeacher(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setMiddleName(teacher.getMiddleName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setChair(teacher.getChair());

        return teacherDTO;
    }

    public static TeacherWithStudentsDTO fromTeacherWithStudents(Teacher teacher){
        TeacherDTO teacherDTO = fromTeacher(teacher);
        TeacherWithStudentsDTO dto = new TeacherWithStudentsDTO(teacherDTO);

        List<StudentDTO> students = teacher.getStudents()
                .stream()
                .map(StudentConvertor::fromStudent)
                .collect(Collectors.toList());

        dto.setStudents(students);
        return dto;
    }

    public static TeacherWithStudentsAndRolesDTO fromTeacherWithStudentsAndRoles(Teacher teacher){
        TeacherWithStudentsDTO teacherStudents = fromTeacherWithStudents(teacher);
        TeacherWithStudentsAndRolesDTO dto = new TeacherWithStudentsAndRolesDTO(teacherStudents);

        List<RoleDTO> roles = teacher.getRoles()
                .stream()
                .map(RoleConvertor::fromRole)
                .collect(Collectors.toList());
        dto.setRoles(roles);
        return dto;
    }
}
