package ru.digitalleague.ligastudent.ligastudent.dto;

import java.util.List;

public class TeacherWithStudentsDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String chair;
    private List<StudentDTO> students;

    public TeacherWithStudentsDTO() {
    }

    public TeacherWithStudentsDTO(TeacherDTO teacherDTO) {
        this.id = teacherDTO.getId();
        this.name = teacherDTO.getName();
        this.middleName = teacherDTO.getMiddleName();
        this.lastName = teacherDTO.getLastName();
        this.chair = teacherDTO.getChair();
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
