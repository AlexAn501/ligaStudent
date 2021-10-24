package ru.digitalleague.ligastudent.ligastudent.convertor;

import ru.digitalleague.ligastudent.ligastudent.dto.StudentDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherWithStudentsDTO;
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
        TeacherWithStudentsDTO dto = new TeacherWithStudentsDTO();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setMiddleName(teacher.getMiddleName());
        dto.setLastName(teacher.getLastName());
        dto.setChair(teacher.getChair());

        List<StudentDTO> students = teacher.getStudents()
                .stream()
                .map(StudentConvertor::fromStudent)
                .collect(Collectors.toList());

        dto.setStudents(students);
        return dto;
    }
}
