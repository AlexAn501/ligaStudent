package ru.digitalleague.ligastudent.ligastudent.dto;

import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherWithStudentsDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String chair;
    private List<StudentDTO> students;


    public static TeacherWithStudentsDTO fromTeacher(Teacher teacher){
        TeacherWithStudentsDTO dto = new TeacherWithStudentsDTO();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setMiddleName(teacher.getMiddleName());
        dto.setLastName(teacher.getLastName());
        dto.setChair(teacher.getChair());

        List<StudentDTO> students = teacher.getStudents()
                .stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());

        dto.setStudents(students);
        return dto;
    }


    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }
}
