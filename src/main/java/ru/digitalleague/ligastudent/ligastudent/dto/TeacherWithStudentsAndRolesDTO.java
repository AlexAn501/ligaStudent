package ru.digitalleague.ligastudent.ligastudent.dto;

import java.util.List;

public class TeacherWithStudentsAndRolesDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String chair;
    private List<StudentDTO> students;
    private List<RoleDTO> roles;


    public TeacherWithStudentsAndRolesDTO() {
    }

    public TeacherWithStudentsAndRolesDTO(TeacherWithStudentsDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.middleName = dto.getMiddleName();
        this.lastName = dto.getLastName();
        this.chair = dto.getChair();
        this.students = dto.getStudents();
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

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
