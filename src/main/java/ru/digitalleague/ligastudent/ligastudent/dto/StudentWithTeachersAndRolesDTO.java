package ru.digitalleague.ligastudent.ligastudent.dto;

import java.util.List;

public class StudentWithTeachersAndRolesDTO {
    private long studentId;
    private String name;
    private String middleName;
    private String lastName;
    private String speciality;
    private int course;
    private String login;
    private List<TeacherDTO> teachers;
    private List<RoleDTO> roles;


    public StudentWithTeachersAndRolesDTO() {
    }

    public StudentWithTeachersAndRolesDTO(StudentWithTeachersDTO dto) {
        this.studentId = dto.getStudentId();
        this.name = dto.getName();
        this.middleName = dto.getMiddleName();
        this.lastName = dto.getLastName();
        this.speciality = dto.getSpeciality();
        this.course = dto.getCourse();
        this.login = dto.getLogin();
        this.teachers = dto.getTeachers();
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
